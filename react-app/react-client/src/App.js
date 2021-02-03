import React, { Component } from "react";

import {
  BrowserRouter as Router,
  Switch,
  Route,
} from "react-router-dom";

import "./App.css";

import Login from "./containers/Login/Login";
import Principal from "./containers/Principal/Principal";
import Reservation from "./containers/Reservation/Reservation";
import CheckIn from "./containers/Check-In/Check-In";
import Registration from './containers/Registration/Registration'
import CheckOut from './containers/Check-Out/Check-Out'
import Reservations from './containers/Reservations/Reservations'
import Products from './containers/Products/Products'
import NewProduct from './containers/Products/NewProduct'
import Orders from './containers/Orders/Orders'
import HelpRequest from './containers/HelpRequest/HelpRequest'



class App extends Component {
  render() {
    return (
      <Router>
        {/* 
        // <Table></Table>
        // <DatePicker label="Desde"></DatePicker>
        //{" "}
        <div>
          // <Button ButtonType="Delete"></Button>
          // <Button ButtonType="Save"></Button>
          // <Button ButtonType="Back"></Button>
          //{" "}
        </div>
        // <Input type="text" label="test"></Input>
        // <Principal></Principal>
        // <Reservation></Reservation> */}
        {/* <Navbar /> */}
        <Switch>
          <Route exact path="/">
            <Principal />
          </Route>
          <Route path="/login">
            <Login />
          </Route>
          <Route path="/checkin">
            <CheckIn/>
          </Route>
          <Route path="/reservation">
            <Reservation/>
          </Route>
          <Route path="/registration">
            <Registration/>
          </Route>
          <Route path="/checkout">
            <CheckOut/>
          </Route>
          <Route path="/reservations">
            <Reservations/>
          </Route>
          <Route path="/products">
            <Products/>
          </Route>
          <Route path="/newProduct">
            <NewProduct/>
          </Route>
          <Route path="/orders">
            <Orders/>
          </Route>
          <Route path="/helprequest">
            <Orders/>
          </Route>
        </Switch>
      </Router>
    );
  }
}

export default App;
