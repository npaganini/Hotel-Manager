import React, { useState } from "react";
import { Container, Row, Col } from "react-bootstrap";
import { makeStyles } from "@material-ui/core/styles";
import { withRouter } from "react-router";


import Navbar from "../../components/Navbar/Navbar";
import Button from "../../components/Button/Button";
import DatePicker from "../../components/DatePickers/DatePicker";
import Input from '../../components/Input/Input'
import Table from '../../components/Table/Table'


const useStyles = makeStyles((theme) => ({
    container: {
        background: "#FAF6FC",
        height: "100vh",
        maxWidth: "100%",
        padding: 0,
    },
    row: {
        marginTop: "40px",
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

    const [surname, setSurname] = useState("");
    const [dateFrom, setDateFrom] = useState(new Date());
    const [dateTo, setDateTo] = useState(new Date());
    const [email, setEmail] = useState("");

    const emailOnChange = (newEmail) => {
        setEmail(newEmail.target.value);
    }

    const dateFromOnChange = (newDateFrom) => {
        setDateFrom(newDateFrom.target.value);
        console.log(dateFrom);

    }

    const dateToOnChange = (newDateTo) => {
        setDateTo(newDateTo.target.value);
        console.log(dateTo);
    }

    const surnameOnChange = (newSurname) => {
        setSurname(newSurname.target.value);
        console.log(surname);
    }

    const onSearchReservations = () => {
        console.log(dateFrom);
        console.log(dateTo);
        console.log(email);
        console.log(surname);

        history.push("/")
    }

    const back = () => {
        history.push("/");
    }

    return (
        <div>
            <Container className={classes.container}>
                <Row>
                    <Col>
                        <Navbar></Navbar>
                    </Col>
                </Row>
                <Row className={classes.row}>
                    <Col xs={12} md={6}>
                        <DatePicker Id="from" label="Desde" onChange={dateFromOnChange}></DatePicker>
                    </Col>
                    <Col xs={12} md={6}>
                        <DatePicker Id="to" label="Hasta" onChange={dateToOnChange}></DatePicker>
                    </Col>
                </Row>
                <Row className={classes.row}>
                    <Col xs={12} md={6}>
                        <Input label="Email" type="email" onChange={emailOnChange} />
                    </Col>
                    <Col xs={12} md={6}>
                        <Input label="Apellido del Huesped" type="text" onChange={surnameOnChange} />
                    </Col>
                </Row>
                <Row className={classes.row} style={{ textAlign: 'center' }}>
                    <Col xs={12} md={6} style={{ textAlign: 'right' }}>
                        <Button ButtonType="Save" onClick={onSearchReservations} ButtonText="Buscar"></Button>
                    </Col>
                    <Col xs={12} md={6} style={{ textAlign: 'left' }}>
                        <Button ButtonType="Back" onClick={back} ButtonText="Volver"></Button>
                    </Col>
                </Row>
                <br></br>
                <Row className="justify-content-sm-center">
                    <Col className={classes.tableCol}>
                        <Table></Table>
                    </Col>
                </Row>
            </Container>
        </div>
    );
};

export default withRouter(Reservations);
