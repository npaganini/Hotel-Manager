import React, { useState } from "react";
import { Container, Row, Col, Card } from "react-bootstrap";
import { makeStyles } from "@material-ui/core/styles";
import { withRouter } from "react-router";

import Table from "../../components/Table/Table";
import Button from "../../components/Button/Button";
import UserNavbar from '../../components/Navbar/UserNavbar'

const useStyles = makeStyles((theme) => ({
    container: {
        background: "#FAF6FC",
        height: "100vh",
    },
}));

const UserPrincipal = ({ history }) => {
    const classes = useStyles();

    const newReservationClick = () => {
        history.push("/reservation")
    }

    return (
        <div>
            <Container fluid="md" className={classes.container}>
                <UserNavbar/>
                <Row className="justify-content-sm-center" style={{ paddingTop: '40px' }}>
                    <Col xs={1} md={2}></Col>
                    <Col xs={10} md={8}>
                        <Table></Table>
                    </Col>
                    <Col xs={1} md={2}></Col>
                </Row>
            </Container>
        </div>
    );
};

export default withRouter(UserPrincipal);
