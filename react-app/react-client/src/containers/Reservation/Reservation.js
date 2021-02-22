import React, { useState } from "react";
import { Container, Row, Col } from "react-bootstrap";
import { makeStyles } from "@material-ui/core/styles";
import { withRouter } from "react-router";
import InfoSimpleDialog from '../../components/Dialog/SimpleDialog';

import { getFreeRooms } from "../../api/roomApi";
import { doReservation } from "../../api/roomApi";
import { useTranslation } from "react-i18next";
import Button from "../../components/Button/Button";
import DatePicker from "../../components/DatePickers/DatePicker";
import Dropdown from "../../components/Dropdown/Dropdown";
import Input from "../../components/Input/Input";

const useStyles = makeStyles((theme) => ({
  container: {
    background: "#FAF6FC",
    height: "100vh",
    maxWidth: "100%",
    padding: 0,
  },
  row: {
    paddingTop: "40px",
    paddingLeft: "10%",
    paddingRight: "10%",
    width: "100%"
  },
  buttonCol: {
    textAlign: "center",
  },
}));

const Reservation = ({ history }) => {
  const classes = useStyles();

  const [showRooms, show] = useState(false);
  const [room, setRoom] = useState("");
  const [options, setOptions] = useState([]);
  const [dateFrom, setDateFrom] = useState(new Date());
  const [dateTo, setDateTo] = useState(new Date());
  const [email, setEmail] = useState("");
  const [errorInput, setErrorInput] = useState(false);
  const [errorDropdown, setErrorDropdown] = useState(false);

  const [showDialog, updateShowDialog] = useState(false);
  const [loading, updateShowLoading] = useState(false);
  const [info, updateInfo] = useState(undefined);
  const { t } = useTranslation();

  const handleDialogClose = () => {
    updateShowDialog(false);
    if (info) {
      history.push("/");
    }
  }

  const emailOnChange = (newEmail) => {
    setEmail(newEmail.target.value);
  };

  const dateFromOnChange = (newDateFrom) => {
    setDateFrom(newDateFrom.target.value);
  };

  const dateToOnChange = (newDateTo) => {
    setDateTo(newDateTo.target.value);
  };

  const showRoomsHandler = (startDate, endDate) => () => {
    getFreeRooms({ startDate, endDate })
      .then((result) => {
        setOptions(result.data);
      })
      .catch((err) => {
        console.log("error", err);
      });
    if (!showRooms) show(true);
  };

  const formIsValidate = () => {
    let isOk = true;
    if (email.length == 0) {
      setErrorInput(true);
      isOk = false;
    }
    if (room.length == 0) {
      isOk = false;
      setErrorDropdown(true);
    }

    return isOk;
  }

  const onSubmitReservation = ({
    startDate,
    endDate,
    userEmail,
    roomId,
  }) => () => {
    if (!formIsValidate())
      return;
    else {
      updateShowLoading(true);
      doReservation({ startDate, endDate, userEmail, roomId })
        .then((response) => {
          updateShowLoading(false);
          updateShowDialog(true);
          updateInfo(response.data);
        })
        .catch((err) => {
          updateShowLoading(false);
          updateShowDialog(true);
          updateInfo(undefined);
          console.log("error", err);
        });
    }
  };


  const onRoomChange = (newRoom) => {
    setRoom(newRoom.target.value);
  };

  const reservationCancel = () => {
    history.push("/");
  };

  return (
    <div>
      <Container className={classes.container}>
        <Row className={classes.row}>
          <Col xs={12} md={6}>
            <DatePicker
              Id="from"
              label={t("room.room.from")}
              onChange={dateFromOnChange}
            ></DatePicker>
          </Col>
          <Col xs={12} md={6}>
            <DatePicker
              Id="to"
              label={t("room.room.until")}
              onChange={dateToOnChange}
            ></DatePicker>
          </Col>
        </Row>
        <Row className={classes.row}>
          <Col xs={12} md={12} className={classes.buttonCol}>
            <Button
              ButtonType="Inherit"
              Id="search-availability"
              ButtonText={t("room.room.filter")}
              onClick={showRoomsHandler(dateFrom, dateTo)}
            ></Button>
          </Col>
        </Row>
        <Row className={classes.row}>
          <Col xs={12} md={2}>
            {showRooms && (
              <Dropdown onChange={onRoomChange} error={errorDropdown} helperText={errorDropdown && t("required")} required={true} options={options} />
            )}
          </Col>
          <Col xs={12} md={6}>
            <Input label={t("room.room.owner")} error={errorInput} helperText={errorInput && t("required")} required={true} type="email" onChange={emailOnChange} />
          </Col>
          <Col xs={6} md={2}>
            <Button
              ButtonType="Save"
              onClick={onSubmitReservation({
                startDate: dateFrom,
                endDate: dateTo,
                userEmail: email,
                roomId: room,
              })}
              ButtonText={t("accept")}
            ></Button>
          </Col>
          <Col xs={6} md={2}>
            <Button
              ButtonType="Back"
              onClick={reservationCancel}
              ButtonText={t("back")}
            ></Button>
          </Col>
        </Row>
        <InfoSimpleDialog open={loading} title={t('loading')} />
        <InfoSimpleDialog open={showDialog} onClose={handleDialogClose} title={info ? "La reserva se realizo correctamente!" : ''}>
          {info ? <div>
            <div>{t("reservation.number")}: {info.reservationHash}</div>
            <div>{t("ratings.roomNumber")}: {info.roomNumber}</div>
          </div> : <div>{t('reservation.checkin.error')}</div>}
        </InfoSimpleDialog>
      </Container>
    </div>
  );
};

export default withRouter(Reservation);
