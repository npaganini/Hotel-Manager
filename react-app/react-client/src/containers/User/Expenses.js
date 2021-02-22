import React, { useState } from "react";
import { Container, Row, Col } from "react-bootstrap";
import { makeStyles } from "@material-ui/core/styles";
import { withRouter } from "react-router";
import { useTranslation } from "react-i18next";

import { getBoughtProducts } from "../../api/userApi";

import Button from "../../components/Button/Button";
import ExpensesTable from "../../components/ExpensesTable/ExpensesTable";

const useStyles = makeStyles((theme) => ({
  container: {
    background: "#FAF6FC",
    height: "100vh",
    maxWidth: "100%",
    padding: 0,
  },
  row: {
    paddingTop: "40px",
    paddingLeft: "10%",
    paddingRight: "10%",
    width: "100%"
  },
  tableCol: {
    paddingRight: "7.5%",
    paddingLeft: "7.5%",
  },
}));

const UserExpenses = ({ history, match }) => {
  const classes = useStyles();

  const [expenses, setExpenses] = useState([]);
  const { t } = useTranslation();

  if (expenses.length === 0) {
    getBoughtProducts(match.params.id)
      .then((response) => {
        setExpenses(
          response.data.map(
            ({ productDescription, productAmount, productPrice }) => ({
              productDescription,
              productAmount,
              productPrice,
            })
          )
        );
      }
    );
  }

  const back = () => {
    history.push("/");
  };

  return (
    <div>
      <Container className={classes.container}>
        <Row className={classes.row}>
          <Col xs={12} md={10} className={classes.tableCol}>
            <ExpensesTable rows={expenses}></ExpensesTable>
          </Col>
          <Col xs={12} md={2}>
            <Col xs={12} md={12} style={{ textAlign: "left" }}>
              <Button
                ButtonType="Back"
                size="large"
                onClick={back}
                ButtonText={t("back")}
              ></Button>
            </Col>
          </Col>
        </Row>
      </Container>
    </div>
  );
};

export default withRouter(UserExpenses);
