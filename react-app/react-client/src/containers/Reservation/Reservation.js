import {React, useState} from 'react';
import { Container, Row, Col } from 'react-bootstrap'
import { makeStyles } from '@material-ui/core/styles';

import Navbar from '../../components/Navbar/Navbar'
import Button from '../../components/Button/Button'
import DatePicker from '../../components/DatePickers/DatePicker'

const useStyles = makeStyles((theme) => ({
    container: {
        background: '#FAF6FC',
        height: '100vh',
        maxWidth: '100%',
        padding: 0
    },
    row: {
        marginTop: '40px',
        paddingLeft: '10%',
        paddingRight: '10%'
    },
    buttonCol:{
        textAlign:'center'
    }
}));

const reservation = (props) => {
    const classes = useStyles();

    const[showRooms, show] = useState(0);

    showRoomsHandler = () => {
        if(state.showRooms)
            show({showRooms: true})
    }

    

    
    return (
        <div>
            <Container className={classes.container}>
                <Row>
                    <Col>
                        <Navbar></Navbar>
                    </Col>
                </Row>
                <Row className={classes.row}>
                    <Col xs={12} md={6}>
                        <DatePicker Id="from" label="Desde"></DatePicker>
                    </Col>
                    <Col xs={12} md={6}>
                        <DatePicker Id="to" label="Hasta"></DatePicker>
                    </Col>
                </Row>
                <Row className={classes.row}>
                    <Col xs={12} md={12} className={classes.buttonCol}>
                        <Button ButtonType="Inherit" Id="search-availability" ButtonText="Buscar Disponibles" onClick={this.showRoomsHandler}></Button>
                    </Col>
                </Row>
                <Row className={classes.row}>
                    <Col xs={12} md={4}>

                    </Col>
                </Row>
            </Container>
        </div>

    );
}

export default reservation;