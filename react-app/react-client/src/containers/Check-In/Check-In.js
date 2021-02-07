import React, {useState} from 'react';
import { Container, Row, Col, Card } from 'react-bootstrap'
import { makeStyles } from '@material-ui/core/styles';
import { withRouter } from "react-router";

import Button from '../../components/Button/Button'
import InfoSimpleDialog from '../../components/Dialog/SimpleDialog';
import Input from '../../components/Input/Input'
import Navbar from '../../components/Navbar/Navbar'
import {doCheckin} from "../../api/roomApi";
import {useTranslation} from "react-i18next";


const useStyles = makeStyles((theme) => ({
    container: {
        background: '#FAF6FC',
        height: '100vh'
    },
    buttonColLeft: {
        textAlign: 'right',
    },
    buttonColRight: {
        textAlign: 'center',
    },
    buttonRow: {
        paddingTop: '20px',
        textAlign: 'center'
    },
    card: {
        paddingTop: '40px',
    }
}));

const checkIn = ({ history }) => {
    const classes = useStyles();
    const [checkIn, onCheckIn] = useState("");
    const [showDialog, updateShowDialog] = useState(false);
    const [info, updateInfo] = useState(null);
    const {t} = useTranslation();

    const onChangeCheckIn = (newCheckIn) => {
        onCheckIn(newCheckIn.target.value);
    }

    const handleDialogClose = () => {
        updateShowDialog(false);
        if (info) {
            history.push("/registration");
        }
    }

    const checkInSubmit = () => {
        doCheckin(checkIn)
            .then((response) => {
                console.log(response)
                // call show dialog in InfoSimpleDialog
                updateShowDialog(true);
                // send result to dialog window to show it
                updateInfo(response.data);
            })
            .catch((error) => {
                updateShowDialog(true);
                updateInfo(null);
                console.log("There was an error processing the request!\n\n", error);
            });
        // Once dialog window closes, redirect to occupants
    }

    const checkInCancel = () => {
        history.push("/");
    }

    return (
        <div>
            <Container fluid="md" className={classes.container}>
                <Row>
                    <Col xs={6} md={3}>
                        <Navbar/>
                    </Col>
                </Row>
                <Row>
                    <Col xs={6} md={3}>
                        <Card className={classes.card}>
                        <Row className={classes.buttonRow}>
                            <Col style={{marginBottom: '5px'}}>
                                <Input label={t('reservation.id')} onChange={onChangeCheckIn}/>
                            </Col>
                            <Col className={classes.buttonColLeft}>
                                <Button ButtonType="Save" onClick={(checkInSubmit)} ButtonText={t('accept')}/>
                            </Col>
                            <Col className={classes.buttonColRight}>
                                <Button ButtonType="Back" onClick={checkInCancel} ButtonText={t('cancel')}/>
                            </Col>
                        </Row>
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
                    <Col xs={6} md={3}/>
                </Row>
            </Container>
        </div>
    );
}

export default withRouter(checkIn);