import React, { useState } from "react";
import { Container, Row, Col } from "react-bootstrap";
import { makeStyles } from "@material-ui/core/styles";
import { withRouter } from "react-router";


import Navbar from "../../components/Navbar/Navbar";
import Button from "../../components/Button/Button";
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
    tableCol: {
        paddingRight: "7.5%",
        paddingLeft: "7.5%",
    },
}));

const Products = ({ history }) => {
    const classes = useStyles();

    const onSubmitReservation = () => {
        console.log(history);
        history.push("/newProduct")
    }

    const reservationCancel = () => {
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
                    <Col xs={12} md={10} className={classes.tableCol}>
                        <Table></Table>
                    </Col>
                    <Col xs={12} md={2}>
                        <Col xs={12} md={6} style={{ textAlign: 'left' }}>
                            <Button ButtonType="Save" size="large" onClick={onSubmitReservation} ButtonText="Agregar"></Button>
                        </Col>
                        <Col xs={12} md={6} style={{ textAlign: 'left' }}>
                            <Button ButtonType="Back" size="large" onClick={reservationCancel} ButtonText="Volver"></Button>
                        </Col>
                    </Col>
                </Row>
            </Container>
        </div>
    );
};

export default withRouter(Products);
