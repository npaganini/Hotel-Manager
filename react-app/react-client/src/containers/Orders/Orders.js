import React, {useState} from "react";
import {Container, Row, Col} from "react-bootstrap";
import {makeStyles} from "@material-ui/core/styles";
import {withRouter} from "react-router";

import Button from "../../components/Button/Button";
import Table from '../../components/Table/Table'
import {useTranslation} from "react-i18next";
import {ordersColumns} from "../../utils/columnsUtil";
import {getAllUndeliveredOrders} from "../../api/roomApi";


const useStyles = makeStyles((theme) => ({
    container: {
        background: "#FAF6FC",
        height: "100vh",
        maxWidth: "100%",
        padding: 0,
    },
    row: {
        paddingTop: "40px",
        paddingLeft: "10%",
        paddingRight: "10%",
    },
    tableCol: {
        paddingRight: "7.5%",
        paddingLeft: "7.5%",
    },
}));

const Orders = ({history}) => {
    const classes = useStyles();
    const {t} = useTranslation();
    const [orders, setOrders] = useState([]);
    const [totalCount, setTotalCount] = useState(0);

    const getUndeliveredOrders = (page, limit) => {
        getAllUndeliveredOrders({page, limit})
            .then((response) => {
                setOrders(response.data);
                setTotalCount(+response.headers["x-total-count"]);
            })
            .catch((error) => {
                console.log("There was an error while fetching all undelivered orders! ", error);
            }
        );
    }

    const onSubmitOrders = () => {
        history.push("/orders")
    }

    const back = () => {
        history.push("/");
    }

    return (
        <div>
            <Container className={classes.container}>
                <Row className={classes.row}>
                    <Col xs={12} md={10} className={classes.tableCol}>
                        <Table columns={ordersColumns} rows={orders} totalItems={totalCount}
                               pageFunction={getUndeliveredOrders}/>
                    </Col>
                    <Col xs={12} md={2}>
                        <Col xs={12} md={6} style={{textAlign: 'left'}}>
                            <Button ButtonType="Save" size="large" onClick={onSubmitOrders} ButtonText={t("refresh")}/>
                        </Col>
                        <Col xs={12} md={6} style={{textAlign: 'left'}}>
                            <Button ButtonType="Back" size="large" onClick={back} ButtonText={t("home")}/>
                        </Col>
                    </Col>
                </Row>
            </Container>
        </div>
    );
};

export default withRouter(Orders);
