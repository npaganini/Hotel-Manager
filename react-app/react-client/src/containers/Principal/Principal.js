import React from 'react';
import { Container, Row, Col } from 'react-bootstrap'
import { makeStyles } from '@material-ui/core/styles';

import Navbar from '../../components/Navbar/Navbar'
import Table from '../../components/Table/Table'
import Button from '../../components/Button/Button'

import classes from './Principal.css';

const useStyles = makeStyles((theme) => ({
    container: {
        background: '#EFDAFC ',
        height: '100vh'
    },
    tableRow: {
        marginTop: '40px',
        marginRight: '0px'
    },
    tableCol:{
        paddingLeft: '7.5%',
    },
    buttonCol:{
        textAlign: 'right',
        paddingRight: '10%'
    },
    buttonRow:{
        marginTop: '40px',
        paddingLeft: '10%'
    }
}));



const principal = (props) => {
    const classes = useStyles();

    return (
        <div className={classes.container}>
            <Container fluid="md">
                <Row>
                    <Col>
                        <Navbar></Navbar>
                    </Col>
                </Row>
                <Row className={classes.buttonRow}>
                    <Col className={classes.buttonCol}>
                    <Button ButtonType="Success" ButtonText="New Reservation"></Button>
                    </Col>
                </Row>
                <Row className={classes.tableRow}>
                    <Col className={classes.tableCol}>
                        <Table></Table>
                    </Col>
                </Row>
            </Container>
        </div>
    );
}

export default principal;