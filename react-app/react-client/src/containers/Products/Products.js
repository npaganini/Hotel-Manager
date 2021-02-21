import React, {useState} from "react";
import {Container, Row, Col} from "react-bootstrap";
import {makeStyles} from "@material-ui/core/styles";
import {withRouter} from "react-router";

import Button from "../../components/Button/Button";
import Table from '../../components/Table/Table'
import {useTranslation} from "react-i18next";
import {getAllProducts} from "../../api/productApi";
import {productsColumns} from "../../utils/columnsUtil";


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
        width: "100%"
    },
    tableCol: {
        paddingRight: "7.5%",
        paddingLeft: "7.5%",
    },
}));

const Products = ({history}) => {
    const classes = useStyles();
    const {t} = useTranslation();
    const [products, setProducts] = useState([]);
    const [totalCount, setTotalCount] = useState(0);

    const getProducts = (page, limit) => {
        getAllProducts({page, limit})
            .then((response) => {
                setProducts(response.data);
                setTotalCount(+response.headers["x-total-count"]);
            })
            .catch((error) => {
                console.log("There was an error while fetching all undelivered orders! ", error);
            }
        );
    }

    const addProduct = () => {
        history.push("/products/newProduct")
    }

    const back = () => {
        history.push("/");
    }

    return (
        <div>
            <Container className={classes.container}>
                <Row className={classes.row}>
                    <Col xs={12} md={10} className={classes.tableCol}>
                        <Table columns={productsColumns} rows={products} totalItems={totalCount}
                               pageFunction={getProducts}/>
                    </Col>
                    <Col xs={12} md={2}>
                        <Col xs={12} md={6} style={{textAlign: 'left'}}>
                            <Button ButtonType="Save" size="large" onClick={addProduct} ButtonText={t("product.add")}/>
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

export default withRouter(Products);
