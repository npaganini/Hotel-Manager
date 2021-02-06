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
    marginTop: "20px",
    textAlign: "center",
  },
}));

const emptyOccupant = { firstName: "", lastName: "" };

const addOccupantDiv = ({ firstName, lastName }, addOccupant, classes) => (
  <div>
    <Col>
      <Input label="First name" value={firstName}></Input>
    </Col>
    <Col>
      <Input label="LastName name" value={lastName}></Input>
    </Col>
    <Col className={classes.buttonColRight}>
      <Button
        ButtonType="Save"
        onClick={addOccupant}
        ButtonText="Add Occupant"
      ></Button>
    </Col>
  </div>
);

const registration = ({ history }) => {
  const classes = useStyles();

  const [checkIn, onCheckIn] = useState("");
  const [submit, onSubmit] = useState(false);
  // occupant = {firstName:..., lastName:...}
  const [occupants, addOccupant] = useState([emptyOccupant]);

  const onChangeCheckIn = (newCheckIn) => {
    onCheckIn(newCheckIn.target.value);
  };

  const checkInSubmit = () => {
    onSubmit(true);
    console.log("history", history);
    console.log(checkIn);
    history.push("/");
  };

  const checkInCancel = () => {
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
                  onChange={onChangeCheckIn}
                ></Input>
              </Col>
              <Col className={classes.buttonColLeft}>
                <Button
                  ButtonType="Save"
                  onClick={checkInSubmit}
                  ButtonText="Registrar"
                ></Button>
              </Col>
              <Col className={classes.buttonColRight}>
                <Button
                  ButtonType="Back"
                  onClick={checkInCancel}
                  ButtonText="Cancelar"
                ></Button>
              </Col>
            </Row>
          </Col>
          <Col xs={6} md={3}></Col>
          {occupants.map((occupant) =>
            addOccupantDiv(occupant, onAddOccupant, classes)
          )}
        </Row>
      </Container>
    </div>
  );
};

export default withRouter(registration);
