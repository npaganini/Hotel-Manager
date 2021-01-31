import React, { useState } from "react";
import Avatar from "@material-ui/core/Avatar";
import Button from "@material-ui/core/Button";
import CssBaseline from "@material-ui/core/CssBaseline";
import TextField from "@material-ui/core/TextField";
import LockOutlinedIcon from "@material-ui/icons/LockOutlined";
import Typography from "@material-ui/core/Typography";
import { makeStyles } from "@material-ui/core/styles";
import Container from "@material-ui/core/Container";
import Card from "@material-ui/core/Card";
import { Row, Col } from "react-bootstrap";
import { useTranslation } from "react-i18next";
import { withRouter } from "react-router";

import { login } from "../../api/loginApi";
import { getAllRooms } from "../../api/roomApi";

const useStyles = makeStyles((theme) => ({
  root: {
    Width: 350,
  },
  paper: {
    marginTop: theme.spacing(8),
    display: "flex",
    flexDirection: "column",
    alignItems: "center",
  },
  avatar: {
    margin: theme.spacing(1),
    backgroundColor: theme.palette.secondary.main,
  },
  form: {
    width: "100%", // Fix IE 11 issue.
    marginTop: theme.spacing(1),
    paddingLeft: "20px",
    paddingRight: "20px",
  },
  submit: {
    width: "150px",
    margin: theme.spacing(3, 0, 2),
  },
  container: {
    background: "#FAF6FC",
    height: "100vh",
  },
  row: {
    paddingTop: "100px",
  },
  col: {
    textAlign: "center",
  },
}));

const onLoginPerformed = (onSubmit, { user, password }) => () => {
  login(user, password)
    .then((result) => {
      window.token = result.data.token;
      window.alert("Logeado pa");
      getAllRooms()
        .then((result) => {
          console.log(result);
        })
        .catch((error) => {
          console.log("room error", error);
        });
      // onSubmit();
    })
    .catch((error) => {
      console.log("there was an error", error);
    });
};

const Login = ({ history }) => {
  const classes = useStyles();
  const { t } = useTranslation();

  const [hasLogged, onSubmit] = useState(false);
  const [user, onChangeUser] = useState("");
  const [password, onChangePassword] = useState("");

  const changeUser = (newUser) => onChangeUser(newUser.target.value);
  const changePassword = (newPassword) =>
    onChangePassword(newPassword.target.value);

  const submitLogin = () => {
    onSubmit(true);
    console.log("history", history);
    history.push("/");
  };

  return (
    <div className={classes.container}>
      <Container fluid="md" component="main" maxWidth="sm">
        <Row className={classes.row}>
          <Col>
            <Card className={classes.root}>
              <CssBaseline />
              <div className={classes.paper}>
                <Avatar className={classes.avatar}>
                  <LockOutlinedIcon />
                </Avatar>
                <Typography component="h1" variant="h5">
                  e-Lobby
                </Typography>
                <form className={classes.form} noValidate>
                  <TextField
                    variant="outlined"
                    margin="normal"
                    required
                    fullWidth
                    id="email"
                    label={t("user.singular")}
                    name="email"
                    autoComplete="email"
                    autoFocus
                    value={user}
                    onChange={changeUser}
                  />
                  <TextField
                    variant="outlined"
                    margin="normal"
                    required
                    fullWidth
                    name="password"
                    label={t("password")}
                    type="password"
                    id="password"
                    autoComplete="current-password"
                    value={password}
                    onChange={changePassword}
                  />
                  <Row>
                    <Col className={classes.col}>
                      <Button
                        type="button"
                        variant="contained"
                        color="primary"
                        className={hasLogged ? classes.submit : classes.submit} // FIXME
                        disabled={hasLogged}
                        onClick={onLoginPerformed(submitLogin, {
                          user,
                          password,
                        })}
                      >
                        Ingresar
                      </Button>
                    </Col>
                  </Row>
                </form>
              </div>
            </Card>
          </Col>
        </Row>
      </Container>
    </div>
  );
};

export default withRouter(Login);
