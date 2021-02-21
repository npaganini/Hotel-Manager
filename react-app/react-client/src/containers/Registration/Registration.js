import React, { useState } from "react";
import { Container, Row, Col, Card } from "react-bootstrap";
import { makeStyles } from "@material-ui/core/styles";
import { withRouter } from "react-router";
import { useTranslation } from "react-i18next";


import Button from "../../components/Button/Button";
import Input from "../../components/Input/Input";
import { registerOccupants } from "../../api/roomApi";

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
    width: "100%",
  },
}));



const emptyOccupant = { firstName: "", lastName: "" };
const emptyOccupantCopy = () => Object.assign({}, ...emptyOccupant);

const addOccupantDiv = (
  index,
  { firstName, lastName },
  classes,
  onFirstNameChange,
  onLastNameChange
) => (
  <div key={index}>
    <Row className={classes.buttonRow}>
      <Col xs={6} md={3}></Col>
      <Col>
        <Input
          label="First name"
          value={firstName}
          onChange={onFirstNameChange}
        />
      </Col>
      <Col>
        <Input
          label="Last Name"
          value={lastName}
          onChange={onLastNameChange}
        />
      </Col>
      <Col xs={6} md={3}></Col>
    </Row>
  </div>
);

const isEmpty = (string) => string == undefined || string.length == 0;

// FIXME add more validation to each occupant
const hasEmptyOccupant = (occupants) =>
  occupants.length == 1 &&
  isEmpty(occupants[0].firstName) &&
  isEmpty(occupants[0].lastName);

const registration = ({ history }) => {
  const classes = useStyles();

  const [reservationId, onReservationId] = useState("");
  const [submit, onSubmit] = useState(false);
  const { t } = useTranslation();
  // occupant = {firstName:..., lastName:...}
  const [occupants, addOccupant] = useState([emptyOccupantCopy()]);

  const onChangeReservationId = (newReservationId) => {
    onReservationId(newReservationId.target.value);
  };

  const registrationSubmit = () => {
    onSubmit(true);
    console.log("registrando");
    const filtederdOccupants = occupants.filter(
      (occupant) => !isEmpty(occupant.firstName) && !isEmpty(occupant.lastName)
    );
    registerOccupants({ occupants: filtederdOccupants }, reservationId)
      .then((response) => {
        history.push("/");
      })
      .catch((error) => {
        console.log("error", error);
      });
    occupants.map((ocup) => console.log(ocup));
  };

  const back = () => {
    history.push("/");
  };

  const onAddOccupant = () => addOccupant([...occupants, emptyOccupantCopy()]);

  const onOccupantChange = (field) => (index) => (event) => {
    console.log("event", event.target.value);
    occupants[index][field] = event.target.value;
    addOccupant([...occupants]);
  };

  console.log("occupants", occupants);

  const onFirstNameChange = onOccupantChange("firstName");
  const onLastNameChange = onOccupantChange("lastName");

  return (
    <div>
      <Container fluid="md" className={classes.container}>
        <Row style={{width: "100%"}}>
          <Col xs={6} md={3}></Col>
          <Col>
            <Row className={classes.buttonRow}>
              <Col style={{ marginBottom: "5px" }}>
                <Input
                  label={t("reservation.number")}
                  onChange={onChangeReservationId}
                ></Input>
              </Col>
              <Col className={classes.buttonColLeft}>
                <Button
                  ButtonType="Save"
                  onClick={registrationSubmit}
                  ButtonText= {t("register")}
                  disabled={
                    submit || hasEmptyOccupant(occupants) || !reservationId
                  }
                ></Button>
              </Col>
              <Col className={classes.buttonColRight}>
                <Button
                  ButtonType="Back"
                  onClick={back}
                  ButtonText={t("cancel")}
                ></Button>
              </Col>
            </Row>
          </Col>
          <Col xs={6} md={3}></Col>
        </Row>
        {occupants.map((occupant, index) =>
          addOccupantDiv(
            index,
            occupant,
            classes,
            onFirstNameChange(index),
            onLastNameChange(index)
          )
        )}
        <Row className={classes.buttonRow}>
          <Col className={classes.buttonColRight}>
            <Button
              ButtonType="Save"
              onClick={onAddOccupant}
              ButtonText={t("registrations.addOccupant")}
            ></Button>
          </Col>
        </Row>
      </Container>
    </div>
  );
};

export default withRouter(registration);
