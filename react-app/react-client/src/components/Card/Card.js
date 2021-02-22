import React, { useState } from "react";
import { Container, Row, Col, Card } from "react-bootstrap";
import { makeStyles } from "@material-ui/core/styles";
import CardActionArea from "@material-ui/core/CardActionArea";
import CardActions from "@material-ui/core/CardActions";
import CardContent from "@material-ui/core/CardContent";
import CardMedia from "@material-ui/core/CardMedia";
import Button from "../Button/Button";
import Typography from "@material-ui/core/Typography";
import { getProductFile } from "../../api/productApi";
import { buyProduct } from "../../api/userApi";
import {useTranslation} from "react-i18next";


const useStyles = makeStyles({
  root: {
    width: "220px",
    marginTop: "15px",
    paddingLeft: "5px",
    paddingRight: "5px",
    height: "350px",
  },
});

const ImgMediaCard = (props) => {
  const classes = useStyles();

  const { id, price, name, reservationId } = props;
  const {t} = useTranslation();


  const onSubmitBuy = (productId) =>
    buyProduct({ productId, reservationId })
      .then((response) => {
      })
      .catch((error) => {
      });

  return (
    <div style={{ height: "400px" }}>
      <Col xs={12} md={3}>
        <Card className={classes.root}>
          <CardActionArea>
            <CardMedia component="img" src={getProductFile(id)} height="140" />
            <CardContent style={{ textAlign: "center" }}>
              <Typography gutterBottom variant="h5" component="h2">
                {name}
              </Typography>
              <Typography variant="h5" component="h2">
                {price}
              </Typography>
            </CardContent>
          </CardActionArea>
          <CardActions style={{ justifyContent: "center" }}>
            <Button
              id={id}
              ButtonType="Save"
              size="large"
              onClick={() => onSubmitBuy(id)}
              ButtonText={t("buy")}
            ></Button>
          </CardActions>
        </Card>
      </Col>
    </div>
  );
};

export default ImgMediaCard;
