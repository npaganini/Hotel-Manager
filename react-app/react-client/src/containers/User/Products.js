import React, { useState } from "react";
import { Container, Row, Col, CardDeck } from "react-bootstrap";
import { makeStyles } from "@material-ui/core/styles";
import { withRouter } from "react-router";

import Card from "../../components/Card/Card";
import { getAllProducts } from "../../api/productApi";

const useStyles = makeStyles((theme) => ({
  container: {
    background: "#FAF6FC",
    height: "100vh",
    paddingTop: "40px",
    maxWidth: "100%",
    paddingLeft: "40px",
    paddingRight: "40px",
    display: "flex",
    justifyContent: "center",
  },
}));

const UserProducts = ({ match, history }) => {
  const classes = useStyles();

  const reservationId = match.params.id;

  const [products, setProducts] = useState([]);

  products.length === 0 &&
    getAllProducts()
      .then((response) => setProducts(response.data))
      .catch((error) => {
        console.log("error", error);
      });

  console.log("products", products);

  return (
    <Container className={classes.container}>
      <CardDeck style={{ justifyContent: "center" }}>
        {products.map(({ id, description, price }) => (
          <Card
            id={id}
            name={description}
            price={"$" + price}
            reservationId={reservationId}
          />
        ))}
      </CardDeck>
    </Container>
  );
};

export default withRouter(UserProducts);
