import React, {useState} from "react";
import {Container, Row, Col} from "react-bootstrap";
import {makeStyles} from "@material-ui/core/styles";
import {withRouter} from "react-router";

import Table from "../../components/Table/Table";
import Button from "../../components/Button/Button";
import {getAllBusyRooms} from "../../api/roomApi";
import {busyRoomsColumns} from "../../utils/columnsUtil";
import {useTranslation} from "react-i18next";

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

const Principal = ({history}) => {
    const classes = useStyles();
    const {t} = useTranslation();
    const [tableInfo, setTableInfo] = useState({busyRooms: [], totalCount: 0});
    const {busyRooms, totalCount} = tableInfo;

    const getAllActiveReservations = (page = 1, limit = 20) => {
        getAllBusyRooms({page, limit})
            .then((response) => {
                setTableInfo({busyRooms: response.data, totalCount: +response.headers["x-total-count"]})
            }
        );
    };

    const newReservationClick = () => {
        history.push("/reservation");
    };

    return (
        <div>
            <Container fluid="md" className={classes.container}>
                <Row className={classes.buttonRow}>
                    <Col className={classes.buttonCol}>
                        <Button
                            ButtonType="Save"
                            onClick={newReservationClick}
                            ButtonText={t("reservation.new")}
                        />
                    </Col>
                    <Col xs={1} md={1}/>
                </Row>
                <Row className="justify-content-sm-center" style={{marginTop: '10px', width: "100%"}}>
                    <Col xs={1} md={1}/>
                    <Col>
                        <Table columns={busyRoomsColumns} rows={busyRooms} totalItems={totalCount}
                               pageFunction={getAllActiveReservations}/>
                    </Col>
                    <Col xs={1} md={1}/>
                </Row>
            </Container>
        </div>
    );
};

export default withRouter(Principal);
