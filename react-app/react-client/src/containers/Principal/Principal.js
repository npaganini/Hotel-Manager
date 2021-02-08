import React from "react";
import { Container, Row, Col } from "react-bootstrap";
import { makeStyles } from "@material-ui/core/styles";
import { withRouter } from "react-router";

import Table from "../../components/Table/Table";
import Button from "../../components/Button/Button";

const useStyles = makeStyles((theme) => ({
  container: {
    background: "#FAF6FC",
    height: "100vh",
  },
  tableCol: {
    paddingRight: "7.5%",
    paddingLeft: "7.5%",
  },
  buttonCol: {
    textAlign: "right",
    paddingRight: "7.5%",
  },
  buttonRow: {
    paddingTop: "40px",
    paddingLeft: "10%",
  },
}));

const Principal = ({history}) => {
  const classes = useStyles();

  const newReservationClick = () => {
    history.push("/reservation")
  }

  return (
    <div>
      <Container fluid="md" className={classes.container}>
        <Row className={classes.buttonRow}>
          <Col className={classes.buttonCol}>
            <Button ButtonType="Inherit" onClick={newReservationClick} ButtonText="New Reservation"></Button>
          </Col>
        </Row>
        <Row className="justify-content-sm-center">
          <Col className={classes.tableCol}>
            <Table></Table>
          </Col>
        </Row>
      </Container>
    </div>
  );
};

export default withRouter(Principal);
