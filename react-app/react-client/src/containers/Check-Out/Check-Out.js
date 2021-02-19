import React, { useState } from 'react';
import { Container, Row, Col, Card } from 'react-bootstrap'
import { makeStyles } from '@material-ui/core/styles';
import { withRouter } from "react-router";


import Navbar from '../../components/Navbar/Navbar'
import Button from '../../components/Button/Button'
import Input from '../../components/Input/Input'
import { useTranslation } from "react-i18next";



const useStyles = makeStyles((theme) => ({
    container: {
        background: '#FAF6FC',
        height: '100vh'
    },
    buttonColLeft: {
        textAlign: 'right',
        paddingTop: '10px'
    },
    buttonColRight: {
        paddingTop: '10px',
        textAlign: 'center',
    },
    buttonRow: {
        textAlign: 'center',
        justifyContent: 'center'
    },
    card: {
        marginTop: '40px',
    }
}));


const checkOut = ({ history }) => {
    const classes = useStyles();

    const [checkOut, onCheckOut] = useState("");
    const [submit, onSubmit] = useState(false);
    const [errorInput, setErrorInput] = useState(false);

    const { t } = useTranslation();

    const onChangeCheckOut = (newCheckOut) => {
        onCheckOut(newCheckOut.target.value);
    }

    const formIsValidate = () => {
        let isOk = true;
        if (checkOut.length == 0) {
            setErrorInput(true);
            isOk = false;
        }

        return isOk;
    }

    const checkOutSubmit = () => {
        if (!formIsValidate())
            return;
        else {
            onSubmit(true);
            console.log("history", history);
            console.log(checkOut);
            history.push("/");
        }
    }

    const checkOutCancel = () => {
        history.push("/");
    }

    return (
        <div>
            <Container fluid="md" className={classes.container}>
                <Row>
                    <Col xs={4} md={2}></Col>
                    <Col>
                        <Card className={classes.card}>
                            <Row className={classes.buttonRow}>
                                <Col xs={12} md={6} style={{ marginBottom: '10px' }}>
                                    <Input label={t('reservation.id')} error={errorInput} helperText={errorInput && "El campo es requerido"} required={true} onChange={onChangeCheckOut}></Input>
                                </Col>
                                <Col xs={6} md={2} className={classes.buttonColLeft}>
                                    <Button ButtonType="Save" onClick={checkOutSubmit} ButtonText="Accept"></Button>
                                </Col>
                                <Col xs={6} md={2} className={classes.buttonColRight}>
                                    <Button ButtonType="Back" onClick={checkOutCancel} ButtonText="Cancel"></Button>
                                </Col>
                            </Row>
                        </Card>
                    </Col>
                    <Col xs={4} md={2}></Col>
                </Row>
            </Container>
        </div>
    );
}

export default withRouter(checkOut);