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
        paddingTop: '20px',
        textAlign: 'center'
    },
    card: {
        paddingTop: '40px',
    }
}));


const checkOut = ({ history }) => {
    const classes = useStyles();

    const [checkOut, onCheckOut] = useState("");
    const [submit, onSubmit] = useState(false);

    const onChangeCheckOut = (newCheckOut) => {
        onCheckOut(newCheckOut.target.value);
    }

    const checkOutSubmit = () => {
        onSubmit(true);
        console.log("history", history);
        console.log(checkOut);
        history.push("/");
    }

    const checkOutCancel = () => {
        history.push("/");
    }

    return (
        <div>
            <Container fluid="md" className={classes.container}>
                <Row>
                    <Col xs={6} md={3}></Col>
                    <Col>
                    <Card className={classes.card}>
                        <Row className={classes.buttonRow}>
                            <Col style={{marginBottom: '5px'}}>
                                <Input label="Reservation Id" onChange={onChangeCheckOut}></Input>
                            </Col>
                            <Col className={classes.buttonColLeft}>
                                <Button ButtonType="Save" onClick={checkOutSubmit} ButtonText="Accept"></Button>
                            </Col>
                            <Col className={classes.buttonColRight}>
                                <Button ButtonType="Back" onClick={checkOutCancel} ButtonText="Cancel"></Button>
                            </Col>
                        </Row>
                    </Card>
                    </Col>
                    <Col xs={6} md={3}></Col>
                </Row>
            </Container>
        </div>
    );
}

export default withRouter(checkOut);