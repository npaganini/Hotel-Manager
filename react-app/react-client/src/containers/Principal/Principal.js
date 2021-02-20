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
    width: '100%',
  },
  buttonCol: {
    textAlign: "right",
  },
  buttonRow: {
    paddingTop: "40px",
    width: "100%",
  },
}));

const Principal = ({ history }) => {
  const classes = useStyles();
  const [busyRooms, setBusyRooms] = useState([]);
  const [totalCount, setTotalCount] = useState(0);

  const newReservationClick = () => {
    history.push("/reservation");
  };

  // this is not ok, it could may be do a request every x seconds, or with a button to refresh
  if (busyRooms.length == 0) {
    getAllBusyRooms()
      .then((response) => {
        setBusyRooms(response.data);
        setTotalCount(response.headers["x-total-count"]);
      })
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
              ButtonType="Save"
              onClick={newReservationClick}
              ButtonText="New Reservation"
            ></Button>
          </Col>
          <Col xs={1} md={1}></Col>
        </Row>
        <Row className="justify-content-sm-center" style={{marginTop:'10px',width: "100%"}}>
          <Col xs={1} md={1}></Col>
          <Col>
            <Table columns={busyRoomsColumns} rows={busyRooms} totalItems={totalCount} />
          </Col>
          <Col xs={1} md={1}></Col>
        </Row>
      </Container>
    </div>
  );
};

export default withRouter(Principal);
