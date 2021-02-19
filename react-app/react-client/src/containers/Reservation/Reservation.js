import React, { useState } from "react";
import { Container, Row, Col } from "react-bootstrap";
import { makeStyles } from "@material-ui/core/styles";
import { withRouter } from "react-router";

import { getFreeRooms } from "../../api/roomApi";
import { doReservation } from "../../api/roomApi";

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
    if (email.length == 0){
      setErrorInput(true);
      isOk = false;
    }
    if(room.length == 0){
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
      doReservation({ startDate, endDate, userEmail, roomId })
        .then((result) => {
          console.log(result);
          history.push("/");
        })
        .catch((err) => {
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
              label="Desde"
              onChange={dateFromOnChange}
            ></DatePicker>
          </Col>
          <Col xs={12} md={6}>
            <DatePicker
              Id="to"
              label="Hasta"
              onChange={dateToOnChange}
            ></DatePicker>
          </Col>
        </Row>
        <Row className={classes.row}>
          <Col xs={12} md={12} className={classes.buttonCol}>
            <Button
              ButtonType="Inherit"
              Id="search-availability"
              ButtonText="Buscar Disponibles"
              onClick={showRoomsHandler(dateFrom, dateTo)}
            ></Button>
          </Col>
        </Row>
        <Row className={classes.row}>
          <Col xs={12} md={2}>
            {showRooms && (
              <Dropdown onChange={onRoomChange} error={errorDropdown} helperText={errorDropdown && "El campo es requerido"} required={true} options={options} />
            )}
          </Col>
          <Col xs={12} md={6}>
            <Input label="Email" error={errorInput} helperText={errorInput && "El campo es requerido"} required={true} type="email" onChange={emailOnChange} />
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
              ButtonText="Accept"
            ></Button>
          </Col>
          <Col xs={6} md={2}>
            <Button
              ButtonType="Back"
              onClick={reservationCancel}
              ButtonText="Cancel"
            ></Button>
          </Col>
        </Row>
      </Container>
    </div>
  );
};

export default withRouter(Reservation);
