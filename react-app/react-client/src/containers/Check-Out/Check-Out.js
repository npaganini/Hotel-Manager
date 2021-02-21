import React, { useState } from 'react';
import { Container, Row, Col, Card } from 'react-bootstrap'
import { makeStyles } from '@material-ui/core/styles';
import { withRouter } from "react-router";

import Navbar from '../../components/Navbar/Navbar';
import Button from '../../components/Button/Button';
import Input from '../../components/Input/Input';
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
        justifyContent: 'center',
        width: "100%"
    },
    card: {
        marginTop: '40px',
    }
}));


const checkOut = ({ history }) => {
    const classes = useStyles();
    const { t } = useTranslation();
    const [checkOut, onCheckOut] = useState("");
    const [errorInput, setErrorInput] = useState(false);

    const onChangeCheckOut = (newCheckOut) => {
        onCheckOut(newCheckOut.target.value);
    }

    const formIsValidate = () => {
        let isOk = true;
        if (checkOut.length === 0) {
            setErrorInput(true);
            isOk = false;
        }

        return isOk;
    }

    const checkOutSubmit = () => {
        if (!formIsValidate())
            return;
        else {
            history.push(`/checkout/${checkOut}/expenses`);
        }
    }

    const checkOutCancel = () => {
        history.push("/");
    }

    return (
        <div>
            <Container fluid="md" className={classes.container}>
                <Row style={{width: "100%"}}>
                    <Col xs={4} md={2}/>
                    <Col>
                        <Card className={classes.card}>
                            <Row className={classes.buttonRow}>
                                <Col xs={12} md={6} style={{ marginBottom: '10px' }}>
                                    <Input label={t("reservation.hash")} error={errorInput} helperText={errorInput && t("required")} required={true} onChange={onChangeCheckOut}/>
                                </Col>
                                <Col xs={6} md={2} className={classes.buttonColLeft}>
                                    <Button ButtonType="Save" onClick={checkOutSubmit} ButtonText={t('accept')}/>
                                </Col>
                                <Col xs={6} md={2} className={classes.buttonColRight}>
                                    <Button ButtonType="Back" onClick={checkOutCancel} ButtonText={t('cancel')}/>
                                </Col>
                            </Row>
                        </Card>
                    </Col>
                    <Col xs={4} md={2}/>
                </Row>
            </Container>
        </div>
    );
}

export default withRouter(checkOut);