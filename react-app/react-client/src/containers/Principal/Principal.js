import React, { useState } from "react";
import { Container, Row, Col } from "react-bootstrap";
import { makeStyles } from "@material-ui/core/styles";
import { withRouter } from "react-router";

import Table from "../../components/Table/Table";
import Button from "../../components/Button/Button";
import { getAllBusyRooms } from "../../api/roomApi";
import { busyRoomsColumns } from "../../utils/columnsUtil";

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

const Principal = ({ history }) => {
  const classes = useStyles();

  const newReservationClick = () => {
    history.push("/reservation");
  };

  const [busyRooms, setBusyRooms] = useState([]);

  // this is not ok, it could may be do a request every x seconds, or with a button to refresh
  if (busyRooms.length == 0) {
    getAllBusyRooms()
      .then((response) => setBusyRooms(response.data))
      .catch((error) => {
        console.log("there was an error on get all busy rooms", error);
      });
  }

  return (
    <div>
      <Container fluid="md" className={classes.container}>
        <Row className={classes.buttonRow}>
          <Col className={classes.buttonCol}>
            <Button
              ButtonType="Inherit"
              onClick={newReservationClick}
              ButtonText="New Reservation"
            ></Button>
          </Col>
        </Row>
        <Row className="justify-content-sm-center">
          <Col className={classes.tableCol}>
            <Table columns={busyRoomsColumns} rows={busyRooms} />
          </Col>
        </Row>
      </Container>
    </div>
  );
};

export default withRouter(Principal);
