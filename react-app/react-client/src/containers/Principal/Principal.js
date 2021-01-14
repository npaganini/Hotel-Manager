import React from 'react';
import { Container, Row, Col } from 'react-bootstrap'
import { makeStyles } from '@material-ui/core/styles';

import Navbar from '../../components/Navbar/Navbar'
import Table from '../../components/Table/Table'
import Button from '../../components/Button/Button'

const useStyles = makeStyles((theme) => ({
    container: {
        background: '#FAF6FC',
        height: '100vh'
    },
    tableRow: {
        marginTop: '40px',
    },
    tableCol:{
        paddingRight: '7.5%',
        paddingLeft: '7.5%'
    },
    buttonCol:{
        textAlign: 'right',
        paddingRight: '7.5%'
    },
    buttonRow:{
        marginTop: '40px',
        paddingLeft: '10%'
    }
}));



const principal = (props) => {
    const classes = useStyles();

    return (
        <div>
            <Container fluid="md" className={classes.container}>
                <Row>
                    <Col>
                        <Navbar></Navbar>
                    </Col>
                </Row>
                <Row className={classes.buttonRow}>
                    <Col className={classes.buttonCol}>
                    <Button ButtonType="Inherit" ButtonText="New Reservation"></Button>
                    </Col>
                </Row>
                <Row className="justify-content-sm-center">
                    <Col className={classes.tableCol}>
                        <Table></Table>
                    </Col>
                </Row>
            </Container>
        </div>
    );
}

export default principal;