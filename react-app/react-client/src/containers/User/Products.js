import React, { useState } from "react";
import { Container, Row, Col, CardDeck } from "react-bootstrap";
import { makeStyles } from "@material-ui/core/styles";
import { withRouter } from "react-router";


import Button from "../../components/Button/Button";
import Card from "../../components/Card/Card";

const useStyles = makeStyles((theme) => ({
    container: {
        background: "#FAF6FC",
        height: "100vh",
        maxWidth: "100%",
        padding: 0,
    },
}));


const UserProducts = ({ history }) => {
    const classes = useStyles();

    return (
        <div>
            <Container className={classes.container}>
                <Row>
                    <Col xs={1} md={2}></Col>
                    <Col xs={10} md={8}>
                        <Card id='1' imagePath='assets/Images/coca.png' name='Coca' price='$7' />
                        <Card id='1' imagePath='assets/Images/coca.png' name='Coca' price='$7' />
                        <Card id='1' imagePath='assets/Images/coca.png' name='Coca' price='$7' />
                        <Card id='1' imagePath='assets/Images/coca.png' name='Coca' price='$7' />
                        <Card id='1' imagePath='assets/Images/coca.png' name='Coca' price='$7' />
                    </Col>
                    <Col xs={1} md={2}></Col>
                </Row>
            </Container>
        </div>
    );
};

export default withRouter(UserProducts);