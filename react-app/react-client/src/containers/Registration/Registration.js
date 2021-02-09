import React, { useState } from "react";
import { Container, Row, Col, Card } from "react-bootstrap";
import { makeStyles } from "@material-ui/core/styles";
import { withRouter } from "react-router";

import Navbar from "../../components/Navbar/Navbar";
import Button from "../../components/Button/Button";
import Input from "../../components/Input/Input";

const useStyles = makeStyles((theme) => ({
    container: {
        background: "#FAF6FC",
        height: "100vh",
    },
    buttonColLeft: {
        textAlign: "right",
    },
    buttonColRight: {
        textAlign: "center",
    },
    buttonRow: {
        paddingTop: "20px",
        textAlign: "center",
    },
}));


const emptyOccupant = { firstName: "", lastName: "" };

const addOccupantDiv = ({ firstName, lastName }, classes) => (
    <div>
        <Row className={classes.buttonRow}>
            <Col xs={6} md={3}></Col>
            <Col>
                <Input label="First name" value={firstName}></Input>
            </Col>
            <Col>
                <Input label="LastName name" value={lastName}></Input>
            </Col>
            <Col xs={6} md={3}></Col>
        </Row>
    </div>
);

const registration = ({ history }) => {
    const classes = useStyles();

    const [reservationId, onReservationId] = useState("");
    const [submit, onSubmit] = useState(false);
    
    // occupant = {firstName:..., lastName:...}
    const [occupants, addOccupant] = useState([emptyOccupant]);

    const onChangeReservationId = (newReservationId) => {
        onReservationId(newReservationId.target.value);
    };

    const registrationSubmit = () => {
        onSubmit(true);
        occupants.map(ocup => console.log(ocup)
        )
        history.push("/");
    };

    const back = () => {
        history.push("/");
    };

    const onAddOccupant = () => addOccupant([...occupants, emptyOccupant]);

    return (
        <div>
            <Container fluid="md" className={classes.container}>
                <Row>
                    <Col xs={6} md={3}></Col>
                    <Col>
                        <Row className={classes.buttonRow}>
                            <Col style={{ marginBottom: "5px" }}>
                                <Input
                                    label="Reservation Id"
                                    onChange={onChangeReservationId}
                                ></Input>
                            </Col>
                            <Col className={classes.buttonColLeft}>
                                <Button
                                    ButtonType="Save"
                                    onClick={registrationSubmit}
                                    ButtonText="Registrar"
                                ></Button>
                            </Col>
                            <Col className={classes.buttonColRight}>
                                <Button
                                    ButtonType="Back"
                                    onClick={back}
                                    ButtonText="Cancelar"
                                ></Button>
                            </Col>
                        </Row>
                    </Col>
                    <Col xs={6} md={3}></Col>
                </Row>
                {occupants.map((occupant) =>
                    addOccupantDiv(occupant, classes)
                )}
                <Row className={classes.buttonRow}>
                    <Col className={classes.buttonColRight}>
                        <Button
                            ButtonType="Save"
                            onClick={onAddOccupant}
                            ButtonText="Add Occupant"
                        ></Button>
                    </Col>
                </Row>
            </Container>
        </div>
    );
};

export default withRouter(registration);
