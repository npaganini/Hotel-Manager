
import React from 'react';
import Avatar from '@material-ui/core/Avatar';
import Button from '@material-ui/core/Button';
import CssBaseline from '@material-ui/core/CssBaseline';
import TextField from '@material-ui/core/TextField';
import LockOutlinedIcon from '@material-ui/icons/LockOutlined';
import Typography from '@material-ui/core/Typography';
import { makeStyles } from '@material-ui/core/styles';
import Container from '@material-ui/core/Container';
import Card from '@material-ui/core/Card';
import { Row, Col } from 'react-bootstrap';

const useStyles = makeStyles((theme) => ({
  root: {
    Width: 350,
  },
  paper: {
    marginTop: theme.spacing(8),
    display: 'flex',
    flexDirection: 'column',
    alignItems: 'center',
  },
  avatar: {
    margin: theme.spacing(1),
    backgroundColor: theme.palette.secondary.main,
  },
  form: {
    width: '100%', // Fix IE 11 issue.
    marginTop: theme.spacing(1),
    paddingLeft: '20px',
    paddingRight: '20px'
  },
  submit: {
    width: '150px',
    margin: theme.spacing(3, 0, 2),
  },
  container: {
    background: '#FAF6FC',
    height: '100vh'
  },
  row: {
    paddingTop: '100px'
  },
  col:{
    textAlign: 'center'
  }
}));

export default function SignIn() {
  const classes = useStyles();

  return (
    <div className={classes.container}>
      <Container fluid="md" component="main" maxWidth="sm" >
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
                    label="Usuario"
                    name="email"
                    autoComplete="email"
                    autoFocus
                  />
                  <TextField
                    variant="outlined"
                    margin="normal"
                    required
                    fullWidth
                    name="password"
                    label="Contraseña"
                    type="password"
                    id="password"
                    autoComplete="current-password"
                  />
                  <Row>
                    <Col className={classes.col}>
                      <Button
                        type="submit"
                        variant="contained"
                        color="primary"
                        className={classes.submit}
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
}