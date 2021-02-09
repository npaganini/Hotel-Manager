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
        paddingTop: '40px',
        maxWidth: "100%",
        paddingLeft: '40px',
        paddingRight: '40px',
        display: "flex",
        justifyContent: 'center',
    },
}));


const UserProducts = ({ history }) => {
    const classes = useStyles();

    return (
        <Container className={classes.container}>
            <CardDeck style={{ justifyContent: 'center' }}>
                <Card id='1' imagePath='assets/Images/coca.png' name='Coca' price='$7' />
                <Card id='2' imagePath='assets/Images/coca.png' name='Coca' price='$7' />
                <Card id='3' imagePath='assets/Images/coca.png' name='Coca' price='$7' />
                <Card id='4' imagePath='assets/Images/coca.png' name='Coca' price='$7' />
                <Card id='5' imagePath='assets/Images/coca.png' name='Coca' price='$7' /> 
            </CardDeck>
        </Container>
    );
};

export default withRouter(UserProducts);