import React, { useState } from 'react';
import { Container, Row, Col, Card } from "react-bootstrap";
import { makeStyles } from '@material-ui/core/styles';
import CardActionArea from '@material-ui/core/CardActionArea';
import CardActions from '@material-ui/core/CardActions';
import CardContent from '@material-ui/core/CardContent';
import CardMedia from '@material-ui/core/CardMedia';
import Button from '../Button/Button';
import Typography from '@material-ui/core/Typography';

const useStyles = makeStyles({
    root: {
        width: '220px',
        marginTop: '15px',
        paddingLeft: '5px',
        paddingRight: '5px',
        height:'350px'
    },
});

const ImgMediaCard = (props) => {
    const classes = useStyles();

    const [product, buyProduct] = useState("")

    const onSubmitBuy = (productId) => {
        buyProduct(productId);
        console.log(product);
    }


    return (
        <div style={{height:'400px'}}>
            <Col xs={12} md={3}>
                <Card className={classes.root}>
                    <CardActionArea>
                        <CardMedia
                            component="img"
                            height="140"
                            image={props.imagePath}
                        />
                        <CardContent style={{ textAlign: 'center' }}>
                            <Typography gutterBottom variant="h5" component="h2">
                                {props.name}
                            </Typography>
                            <Typography variant="h5" component="h2">
                                {props.price}
                            </Typography>
                        </CardContent>
                    </CardActionArea>
                    <CardActions style={{justifyContent: 'center'}}>
                        <Button id={props.id} ButtonType="Save" size="large" onClick={() => onSubmitBuy(props.id)} ButtonText="Comprar"></Button>
                    </CardActions>
                </Card>
            </Col>
        </div>
    );
}

export default ImgMediaCard;
