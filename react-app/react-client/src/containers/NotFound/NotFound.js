import React from 'react';
import { Container, Row, Col, Card } from 'react-bootstrap'
import { makeStyles } from '@material-ui/core/styles';
import { withRouter } from "react-router";

import Button from '../../components/Button/Button'
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

const NotFound = ({ history }) => {
    const classes = useStyles();
    const { t } = useTranslation();

    const back = () => {
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
                                <Col xs={12} md={12} style={{ marginTop: '30px' }}>
                                    <h1 style={{font:'arial', color:'grey'}}>Resource not found</h1>
                                </Col>
                            </Row>
                            <Row style={{width: "100%"}}>
                            <Col xs={12} md={12} style={{marginTop:'10px',textAlign:'center',justifyContent:'center'}}>
                                    <Button ButtonType="Save" onClick={(back)} ButtonText='Volver a Inicio' />
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

export default withRouter(NotFound);