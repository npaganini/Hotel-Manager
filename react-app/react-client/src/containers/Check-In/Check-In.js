import React, { useState } from 'react';
import { Container, Row, Col, Card } from 'react-bootstrap'
import { makeStyles } from '@material-ui/core/styles';
import { withRouter } from "react-router";

import Button from '../../components/Button/Button'
import InfoSimpleDialog from '../../components/Dialog/SimpleDialog';
import Input from '../../components/Input/Input'
import { doCheckin } from "../../api/roomApi";
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
        textAlign: 'center',
        paddingTop: '10px'
    },
    buttonRow: {
        textAlign: 'center',
        justifyContent: 'center',
        width: "100%",
    },
    card: {
        marginTop: '40px',
    }
}));

const checkIn = ({ history }) => {
    const classes = useStyles();
    const { t } = useTranslation();
    const [checkIn, onCheckIn] = useState("");
    const [showDialog, updateShowDialog] = useState(false);
    const [loading, updateShowLoading] = useState(false);
    const [info, updateInfo] = useState(undefined);
    const [errorInput, setErrorInput] = useState(false);


    const onChangeCheckIn = (newCheckIn) => {
        onCheckIn(newCheckIn.target.value);
    }

    const handleDialogClose = () => {
        updateShowDialog(false);
        if (info) {
            history.push("/registration");
        }
    }

    const formIsValidate = () => {
        let isOk = true;
        if (checkIn.length == 0) {
            setErrorInput(true);
            isOk = false;
        }

        return isOk;
    }
    
    const checkInSubmit = () => {
        if (!formIsValidate())
            return;
        else {
            updateShowLoading(true);
            doCheckin(checkIn)
                .then((response) => {
                    updateShowLoading(false);
                    console.log(response)
                    // call show dialog in InfoSimpleDialog
                    updateShowDialog(true);
                    // send result to dialog window to show it
                    updateInfo(response.data);
                })
                .catch((error) => {
                    updateShowLoading(false);
                    updateShowDialog(true);
                    updateInfo(undefined);
                    console.log("There was an error processing the request!\n\n", error);
                });
            // Once dialog window closes, redirect to occupants
        }
    }

    const checkInCancel = () => {
        history.push("/");
    }

    return (
        <div>
            <Container fluid="md" className={classes.container}>
                <Row style={{width: "100%"}}>
                    <Col xs={4} md={2}></Col>
                    <Col>
                        <Card className={classes.card}>
                            <Row className={classes.buttonRow}>
                                <Col xs={12} md={6} style={{ marginBottom: '10px' }}>
                                    <Input label={t('reservation.id')} error={errorInput} helperText={errorInput && "El campo es requerido"} required={true} onChange={onChangeCheckIn} />
                                </Col>
                                <Col xs={6} md={2} className={classes.buttonColLeft}>
                                    <Button ButtonType="Save" onClick={(checkInSubmit)} ButtonText={t('accept')} />
                                </Col>
                                <Col xs={6} md={2} className={classes.buttonColRight}>
                                    <Button ButtonType="Back" onClick={checkInCancel} ButtonText={t('cancel')} />
                                </Col>
                            </Row>
                            <InfoSimpleDialog open={loading} title={t('loading')} />
                            <InfoSimpleDialog open={showDialog} onClose={handleDialogClose} title={info ? t('reservation.checkin.successful') : ''}>
                                {info ? <div>
                                    <div>{t('reservation.id')}: {info.hash}</div>
                                    <div>{t('email')}: {info.userEmail}</div>
                                    <div>{t('reservation.date.start')}: {(new Date(info.startDate)).toLocaleDateString()}</div>
                                    <div>{t('reservation.date.end')}: {(new Date(info.endDate)).toLocaleDateString()}</div>
                                    <div>{t('reservation.room.number')}: {info.room.number}</div>
                                    <div>{t('reservation.room.type')}: {t(`room.roomType.${info.room.roomType.toLowerCase()}`)}</div>
                                </div> : <div>{t('reservation.checkin.error')}</div>}
                            </InfoSimpleDialog>
                        </Card>
                    </Col>
                    <Col xs={4} md={2}></Col>
                </Row>
            </Container>
        </div>
    );
}

export default withRouter(checkIn);