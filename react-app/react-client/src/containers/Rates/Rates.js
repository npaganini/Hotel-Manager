import React, { useState } from "react";
import { Container, Row, Col } from "react-bootstrap";
import { makeStyles } from "@material-ui/core/styles";
import { withRouter } from "react-router";

import Button from "../../components/Button/Button";
import DatePicker from "../../components/DatePickers/DatePicker";
import Input from '../../components/Input/Input'
import Table from '../../components/Table/Table'
import { useTranslation } from "react-i18next";
import { rateColumns } from "../../utils/columnsUtil";
import Progress from "../../components/Progress/Progress"



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
    tableCol: {
        paddingRight: "7.5%",
        paddingLeft: "7.5%",
    },
}));

const Rates = ({ history }) => {
    const classes = useStyles();
    const { t } = useTranslation();
    const [startDate, setDateFrom] = useState(new Date());
    const [endDate, setDateTo] = useState(new Date());
    const [email, setEmail] = useState("");
    const [errorStatusCode, setErrorStatusCode] = useState(200);
    const [tableInfo, setTableInfo] = useState({ reservations: [], totalCount: 0 });
    const { reservations, totalCount } = tableInfo;

    const emailOnChange = (newEmail) => {
        setEmail(newEmail.target.value);
    }

    const dateFromOnChange = (newDateFrom) => {
        setDateFrom(newDateFrom.target.value);
    }

    const dateToOnChange = (newDateTo) => {
        setDateTo(newDateTo.target.value);
    }

    const onSearchReservations = () => {
        // getAllReservationsFiltered(1, 20, startDate, endDate, email, lastName);
    }

    const back = () => {
        history.push("/");
    }

    return (
        <div>
            <Container fluid="md" className={classes.container}>
                <Row className={classes.row}>
                    <Col xs={12} md={6}>
                        <DatePicker Id="from" label={t("reservation.date.start")} onChange={dateFromOnChange} />
                    </Col>
                    <Col xs={12} md={6}>
                        <DatePicker Id="to" label={t("reservation.date.end")} onChange={dateToOnChange} />
                    </Col>
                </Row>
                <Row className={classes.row}>
                    <Col xs={12} md={6}>
                        <Input label="Search..." type="email" onChange={emailOnChange} />
                    </Col>
                    <Col xs={6} md={3} style={{ textAlign: 'right' }}>
                        <Button ButtonType="Save" onClick={onSearchReservations} ButtonText={t("search")} />
                    </Col>
                    <Col xs={6} md={3} style={{ textAlign: 'center' }}>
                        <Button ButtonType="Back" onClick={back} ButtonText={t("user.home")} />
                    </Col>
                </Row>
                <Row className={classes.row} style={{ textAlign: 'center' }}>

                </Row>
                <br />
                <Row className={classes.row}>
                    <Col xs={12} md={4} style={{ textAlign: 'center' }}>
                        Rate Promedio:
                    </Col>
                    <Col xs={12} md={8}>
                        <Progress progress={3.14} />
                    </Col>
                </Row>
                <br />
                <Row className="justify-content-sm-center" style={{ background: "#FAF6FC",width: "100%" }}>
                    <Col className={classes.tableCol}>
                        <Table columns={rateColumns} rows={reservations} totalItems={totalCount} />
                    </Col>
                </Row>
            </Container>
        </div>
    );
};

export default withRouter(Rates);
