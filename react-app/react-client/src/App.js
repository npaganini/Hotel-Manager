import React, { Component } from "react";

import { BrowserRouter as Router, Switch, Route } from "react-router-dom";

import "./App.css";

import Login from "./containers/Login/Login";
import Principal from "./containers/Principal/Principal";
import Reservation from "./containers/Reservation/Reservation";
import CheckIn from "./containers/Check-In/Check-In";
import Registration from "./containers/Registration/Registration";
import CheckOut from "./containers/Check-Out/Check-Out";
import Reservations from "./containers/Reservations/Reservations";
import Products from "./containers/Products/Products";
import NewProduct from "./containers/Products/NewProduct";
import Orders from "./containers/Orders/Orders";
import HelpRequest from "./containers/HelpRequest/HelpRequest";
import Navbar from "./components/Navbar/Navbar";
import UserPrincipal from './containers/User/Principal'
import UserHelp from './containers/User/Help'
import UserProducts from './containers/User/Products'
import UserExpenses from './containers/User/Expenses'

class App extends Component {
  render() {
    return (
      <Router>
        <Navbar></Navbar>

        <Switch>
          <Route exact path="/">
            <Principal />
          </Route>
          <Route path="/login">
            <Login />
          </Route>
          <Route path="/checkin">
            <CheckIn />
          </Route>
          <Route path="/reservation">
            <Reservation />
          </Route>
          <Route path="/registration">
            <Registration />
          </Route>
          <Route path="/checkout">
            <CheckOut />
          </Route>
          <Route path="/reservations">
            <Reservations />
          </Route>
          <Route path="/products">
            <Products />
          </Route>
          <Route path="/newProduct">
            <NewProduct />
          </Route>
          <Route path="/orders">
            <Orders />
          </Route>
          <Route path="/helprequest">
            <HelpRequest />
          </Route>
          <Route path="/userprincipal">
            <UserPrincipal />
          </Route>
          <Route path="/userhelp">
            <UserHelp />
          </Route>
          <Route path="/userproducts">
            <UserProducts />
          </Route>         
          <Route path="/userexpenses">
            <UserExpenses />
          </Route>
        </Switch>
      </Router>
    );
  }
}

export default App;
