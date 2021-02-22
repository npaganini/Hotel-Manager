import React, {useState} from "react";
import {Col, Container, Row} from "react-bootstrap";
import {makeStyles} from "@material-ui/core/styles";
import {withRouter} from "react-router";

import {getAllReservations} from "../../api/userApi";
import {reservationUserColumns} from "../../utils/columnsUtil";
import Table from "../../components/Table/Table";

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

  const getMyReservations = () => {
    getAllReservations().then((response) => {
        setReservations(
        response.data.activeReservations.map(
          ({ roomType, startDate, endDate, roomNumber, reservationId }) => {
              return Object.assign(
                {},
                {roomType, startDate, endDate, roomNumber},
                {
                    actions: () => history.push(`/products/${reservationId}`),
                    expenses: () => history.push(`/expenses/${reservationId}`),
                    help: () => history.push(`/help/${reservationId}`),
                }
            );
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
          style={{ paddingTop: "40px",width: "100%" }}
        >
          <Col xs={1} md={1}/>
          <Col xs={10} md={10}>
            <Table
              columns={reservationUserColumns}
              rows={reservations}
              totalItems={totalCount}
              pageFunction={getMyReservations}
            />
          </Col>
          <Col xs={1} md={1}/>
        </Row>
      </Container>
    </div>
  );
};

export default withRouter(UserPrincipal);
