import React, { useState } from "react";
import { Container, Row, Col } from "react-bootstrap";
import { makeStyles } from "@material-ui/core/styles";
import { withRouter } from "react-router";


import Navbar from "../../components/Navbar/Navbar";
import Button from "../../components/Button/Button";
import Input from '../../components/Input/Input'


const useStyles = makeStyles((theme) => ({
    container: {
        background: "#FAF6FC",
        height: "100vh",
        maxWidth: "100%",
        padding: 0,
    },
    row: {
        marginTop: "40px",
        paddingLeft: "10%",
        paddingRight: "10%",
    },
    buttonCol: {
        textAlign: "center",
    },
}));

const NewProduct = ({ history }) => {
    const classes = useStyles();

    const [description, setDescription] = useState("");
    const [photo, setPhoto] = useState("");
    const [price, setPrice] = useState("");


    const descriptionOnChange = (description) => {
        setDescription(description.target.value);
    }

    const photoOnChange = (newPhoto) => {
        setPhoto(newPhoto.target.value);
    }

    const priceOnChange = (newPrice) => {
        setPrice(newPrice.target.value);
    }

    const onSubmitReservation = () => {
        history.push("/products")
    }

    const reservationCancel = () => {
        history.push("/products");
    }

    return (
        <div>
            <Container className={classes.container}>
                <Row className={classes.row}>
                    <Col xs={12} md={4}>
                        <Input label="Descripcion" type="text" onChange={descriptionOnChange} />

                    </Col>
                    <Col xs={12} md={4}>
                        <Input label="Foto" type="text" onChange={photoOnChange} />
                    </Col>
                    <Col xs={12} md={4}>
                        <Input label="Precio" type="number" onChange={priceOnChange} />
                    </Col>
                </Row>
                <Row className={classes.row}>
                    <Col xs={12} md={6} style={{textAlign:'right'}}>
                        <Button ButtonType="Save" onClick={onSubmitReservation} ButtonText="Crear"></Button>
                    </Col>
                    <Col xs={12} md={6}>
                        <Button ButtonType="Back" onClick={reservationCancel} ButtonText="Cancelar"></Button>
                    </Col>
                </Row>
            </Container>
        </div>
    );
};

export default withRouter(NewProduct);