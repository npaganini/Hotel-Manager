import {React, useState} from 'react';
import { Container, Row, Col } from 'react-bootstrap'
import { makeStyles } from '@material-ui/core/styles';

import Navbar from '../../components/Navbar/Navbar'
import Button from '../../components/Button/Button'
import DatePicker from '../../components/DatePickers/DatePicker'
import Dropdown from '../../components/Dropdown/Dropdown';

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

    const[showRooms, show] = useState(false);

    const showRoomsHandler = () => {
        if({showRooms})
            show({showRooms: true});
    }

    let availableRooms = null;

    // if({showRooms}){
    //     availableRooms = <Dropdown/>;
    // }

    
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
                        <Button ButtonType="Inherit" Id="search-availability" ButtonText="Buscar Disponibles" onClick={showRoomsHandler}></Button>
                    </Col>
                </Row>
                <Row className={classes.row}>
                    <Col xs={12} md={4}>
                        {availableRooms}
                    </Col>
                </Row>
            </Container>
        </div>

    );
}

export default reservation;