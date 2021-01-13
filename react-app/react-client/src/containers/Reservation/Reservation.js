import React from 'react';
import { Container, Row, Col } from 'react-bootstrap'
import { makeStyles } from '@material-ui/core/styles';

import Navbar from '../../components/Navbar/Navbar'
import Button from '../../components/Button/Button'
import DatePicker from '../../components/DatePickers/DatePicker'

const useStyles = makeStyles((theme) => ({
    container: {
        background: '#FAF6FC',
        height: '100vh',
        padding: 0,
        margin: 0
    },
    buttonRow: {
        marginTop: '40px',
        paddingLeft: '10%',
        paddingRight: '10%'
    }
}));

const reservation = (props) => {
    const classes = useStyles();

    return (
        <div>
            <Container className={classes.container}>
                <Row>
                    <Col>
                        <Navbar></Navbar>
                    </Col>
                </Row>
                <Row className={classes.buttonRow}>
                    <Col>
                        <DatePicker xs={6} md={4} lg={2} Id="from" label="Desde"></DatePicker>
                    </Col>
                    <Col>
                        <DatePicker xs={6} md={4} lg={2} Id="to" label="Hasta"></DatePicker>
                    </Col>
                </Row>
            </Container>
        </div>

    );
}

export default reservation;