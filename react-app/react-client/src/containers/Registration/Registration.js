import React, {useState} from 'react';
import { Container, Row, Col, Card } from 'react-bootstrap'
import { makeStyles } from '@material-ui/core/styles';
import { withRouter } from "react-router";


import Navbar from '../../components/Navbar/Navbar'
import Button from '../../components/Button/Button'
import Input from '../../components/Input/Input'


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
        marginTop: '20px',
        textAlign: 'center'
    }
}));


const registration = ({ history }) => {
    const classes = useStyles();

    const [checkIn, onCheckIn] = useState("");
    const [submit, onSubmit] = useState(false);

    const onChangeCheckIn = (newCheckIn) => {
        onCheckIn(newCheckIn.target.value);
    }

    const checkInSubmit = () => {
        onSubmit(true);
        console.log("history", history);
        console.log(checkIn);
        history.push("/");
    }

    const checkInCancel = () => {
        history.push("/");
    }

    return (
        <div>
            <Container fluid="md" className={classes.container}>
                <Row>
                    <Col>
                        <Navbar></Navbar>
                    </Col>
                </Row>
                <Row>
                    <Col xs={6} md={3}></Col>
                    <Col>
                        <Row className={classes.buttonRow}>
                            <Col style={{marginBottom: '5px'}}>
                                <Input label="Reservation Id" onChange={onChangeCheckIn}></Input>
                            </Col>
                            <Col className={classes.buttonColLeft}>
                                <Button ButtonType="Save" onClick={checkInSubmit} ButtonText="Registrar"></Button>
                            </Col>
                            <Col className={classes.buttonColRight}>
                                <Button ButtonType="Back" onClick={checkInCancel} ButtonText="Cancelar"></Button>
                            </Col>
                        </Row>
                    </Col>
                    <Col xs={6} md={3}></Col>
                </Row>
            </Container>
        </div>
    );
}

export default withRouter(registration);