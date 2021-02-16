import React, { useState } from "react";
import { Container, Row, Col, Card } from "react-bootstrap";
import { Redirect } from "react-router-dom";
import { makeStyles } from "@material-ui/core/styles";
import { withRouter } from "react-router";

import { getAllReservations } from "../../api/userApi";
import { reservationUserColumns } from "../../utils/columnsUtil";

import Table from "../../components/Table/Table";
import UserNavbar from "../../components/Navbar/UserNavbar";

const useStyles = makeStyles((theme) => ({
  container: {
    background: "#FAF6FC",
    height: "100vh",
  },
}));

const UserPrincipal = ({ history }) => {
  const classes = useStyles();
  const [reservations, setReservations] = useState([]);
  const [totalCount, setTotalCount] = useState(0);

  if (reservations.length == 0) {
    getAllReservations().then((response) => {
      setReservations(
        response.data.activeReservations.map(
          ({ roomType, startDate, endDate, roomNumber, reservationId }) => {
            const newObject = Object.assign(
              {},
              { roomType, startDate, endDate, roomNumber },
              {
                actions: () => history.push(`/products/${reservationId}`),
                expenses: () => history.push(`/expenses/${reservationId}`),
                help: () => history.push(`/help/${reservationId}`),
              }
            );
            console.log("newObject", newObject);
            return newObject;
          }
        )
      );
    });
  }

  return (
    <div>
      <Container fluid="md" className={classes.container}>
        <Row
          className="justify-content-sm-center"
          style={{ paddingTop: "40px" }}
        >
          <Col xs={1} md={2}></Col>
          <Col xs={10} md={8}>
            <Table
              columns={reservationUserColumns}
              rows={reservations}
              totalItems={totalCount}
            ></Table>
          </Col>
          <Col xs={1} md={2}></Col>
        </Row>
      </Container>
    </div>
  );
};

export default withRouter(UserPrincipal);
