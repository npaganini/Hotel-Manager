import React, { useState } from "react";
import { Container, Row, Col } from "react-bootstrap";
import { makeStyles } from "@material-ui/core/styles";
import { withRouter } from "react-router";

import {doCheckout} from '../../api/roomApi';


import Button from "../../components/Button/Button";
import ExpensesTable from "../../components/ExpensesTable/ExpensesTable";

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
    tableCol: {
        paddingRight: "7.5%",
        paddingLeft: "7.5%",
    },
}));

const CheckOutExpenses = ({ history, match }) => {
    const classes = useStyles();

    const [expenses, setExpenses] = useState([]);

    if (expenses.length === 0) {
        doCheckout({},match.params.reservationId)
            .then((response) => {
                console.log(response);
                setExpenses(
                    response.data.map(
                        ({ productDescription, productAmount, productPrice }) => ({
                            productDescription,
                            productAmount,
                            productPrice,
                        })
                    )
                );
            })
            .catch((error) => {
                console.log("there was an error on get all expenses", error);
            });
    }

    const back = () => {
        history.push("/");
    };

    return (
        <div>
            <Container className={classes.container}>
                <Row className={classes.row}>
                    <Col xs={12} md={10} className={classes.tableCol}>
                        <ExpensesTable rows={expenses}></ExpensesTable>
                    </Col>
                    <Col xs={12} md={2}>
                        <Col xs={12} md={12} style={{ textAlign: "left" }}>
                            <Button
                                ButtonType="Back"
                                size="large"
                                onClick={back}
                                ButtonText="Salir"
                            ></Button>
                        </Col>
                    </Col>
                </Row>
            </Container>
        </div>
    );
};

export default withRouter(CheckOutExpenses);
