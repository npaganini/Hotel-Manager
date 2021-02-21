import React, { useState } from "react";
import { Container, Row, Col } from "react-bootstrap";
import { makeStyles } from "@material-ui/core/styles";
import { withRouter } from "react-router";

import Button from "../../components/Button/Button";
import DatePicker from "../../components/DatePickers/DatePicker";
import Input from '../../components/Input/Input'
import Table from '../../components/Table/Table'
import {useTranslation} from "react-i18next";
import {getAllReservations} from "../../api/roomApi";
import {reservationsColumns} from "../../utils/columnsUtil";
import InfoSimpleDialog from "../../components/Dialog/SimpleDialog";


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
    tableCol: {
        paddingRight: "7.5%",
        paddingLeft: "7.5%",
    },
}));

const Reservations = ({ history }) => {
    const classes = useStyles();
    const { t } = useTranslation();
    const [lastName, setLastName] = useState("");
    const [startDate, setDateFrom] = useState(new Date());
    const [endDate, setDateTo] = useState(new Date());
    const [email, setEmail] = useState("");
    const [showDialog, updateShowDialog] = useState(false);
    const [errorStatusCode, setErrorStatusCode] = useState(200);
    const [tableInfo, setTableInfo] = useState({reservations: [], totalCount: 0});
    const {reservations, totalCount} = tableInfo;

    const getAllReservationsFiltered = (page = 1, limit = 20, startDate, endDate, email, lastName) => {
        getAllReservations({page, limit, startDate, endDate, email, lastName})
            .then((response) => {
                console.log(response.data);
                setTableInfo({reservations: response.data, totalCount: +response.headers["x-total-count"]})
            }).catch((error) => {
                setErrorStatusCode(error.response.status);
                updateShowDialog(true);
                console.log("There was an error while fetching all busy rooms! ", error);
            }
        );
    };

    const handleDialogClose = () => {
        updateShowDialog(false);
    }

    const emailOnChange = (newEmail) => {
        setEmail(newEmail.target.value);
    }

    const dateFromOnChange = (newDateFrom) => {
        setDateFrom(newDateFrom.target.value);
    }

    const dateToOnChange = (newDateTo) => {
        setDateTo(newDateTo.target.value);
    }

    const lastNameOnChange = (newLastName) => {
        setLastName(newLastName.target.value);
    }

    const onSearchReservations = () => {
        getAllReservationsFiltered(1, 20, startDate, endDate, email, lastName);
    }

    const back = () => {
        history.push("/");
    }

    return (
        <div>
            <Container fluid="md" className={classes.container}>
                <Row className={classes.row}>
                    <Col xs={12} md={6}>
                        <DatePicker Id="from" label={t("reservation.date.start")} onChange={dateFromOnChange}/>
                    </Col>
                    <Col xs={12} md={6}>
                        <DatePicker Id="to" label={t("reservation.date.end")} onChange={dateToOnChange}/>
                    </Col>
                </Row>
                <Row className={classes.row}>
                    <Col xs={12} md={6}>
                        <Input label={t("reservation.email")} type="email" onChange={emailOnChange} />
                    </Col>
                    <Col xs={12} md={6}>
                        <Input label={t("lastname")} type="text" onChange={lastNameOnChange} />
                    </Col>
                </Row>
                <Row className={classes.row} style={{ textAlign: 'center' }}>
                    <Col xs={12} md={6} style={{ textAlign: 'right' }}>
                        <Button ButtonType="Save" onClick={onSearchReservations} ButtonText={t("search")}/>
                    </Col>
                    <Col xs={12} md={6} style={{ textAlign: 'left' }}>
                        <Button ButtonType="Back" onClick={back} ButtonText={t("user.home")}/>
                    </Col>
                </Row>
                <br/>
                <InfoSimpleDialog open={showDialog} onClose={handleDialogClose}>
                    {errorStatusCode === 400 ? t("reservation.date.required") : t("reservation.checkin.error")}
                </InfoSimpleDialog>
                <br/>
                <Row className="justify-content-sm-center" style={{background: "#FAF6FC"}}>
                    <Col className={classes.tableCol}>
                        <Table columns={reservationsColumns} rows={reservations} totalItems={totalCount} pageFunction={getAllReservationsFiltered}/>
                    </Col>
                </Row>
            </Container>
        </div>
    );
};

export default withRouter(Reservations);
