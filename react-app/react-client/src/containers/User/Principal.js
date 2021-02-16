import React, { useState } from "react";
import { Container, Row, Col, Card } from "react-bootstrap";
import { makeStyles } from "@material-ui/core/styles";
import { withRouter } from "react-router";

import { getAllReservations } from "../../api/userApi";
import { reservationUserColumns } from "../../utils/columnsUtil";

import Table from "../../components/Table/Table";
import Button from "../../components/Button/Button";
import UserNavbar from '../../components/Navbar/UserNavbar'

const useStyles = makeStyles((theme) => ({
    container: {
        background: "#FAF6FC",
        height: "100vh",
    },
}));

let rows = [];

const UserPrincipal = ({ history }) => {
    const classes = useStyles();
    const [reservations, setReservations] = useState([]);
    const [totalCount, setTotalCount] = useState(0);

    // getAllReservations()
    //     .then((response) => {
    //         response.data.forEach(element => {
    //             rows.push({element.roomType, element.startDate, element.endDate, element.roomNumber,
    //                 <Button ButtonType="Back" size="large" onClick={back} ButtonText="Volver" />
    //             });
    //         });
    //     });

    return (
        <div>
            <Container fluid="md" className={classes.container}>
                <UserNavbar />
                <Row className="justify-content-sm-center" style={{ paddingTop: '40px' }}>
                    <Col xs={1} md={2}></Col>
                    <Col xs={10} md={8}>
                        <Table columns={reservationUserColumns} rows={reservations} totalItems={totalCount}></Table>
                    </Col>
                    <Col xs={1} md={2}></Col>
                </Row>
            </Container>
        </div>
    );
};

export default withRouter(UserPrincipal);
