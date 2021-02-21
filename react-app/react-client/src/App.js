import React, { Component, useState } from "react";

import { BrowserRouter as Router, Switch, Route } from "react-router-dom";
import { PrivateRoute } from "./components/PrivateRoute/PrivateRoute";

import "./App.css";

import Login from "./containers/Login/Login";
import Principal from "./containers/Principal/Principal";
import Reservation from "./containers/Reservation/Reservation";
import CheckIn from "./containers/Check-In/Check-In";
import Registration from "./containers/Registration/Registration";
import CheckOut from "./containers/Check-Out/Check-Out";
import CheckOutExpenses from "./containers/Check-Out/Check-Out-Expenses";
import Reservations from "./containers/Reservations/Reservations";
import Products from "./containers/Products/Products";
import NewProduct from "./containers/Products/NewProduct";
import Orders from "./containers/Orders/Orders";
import HelpRequest from "./containers/HelpRequest/HelpRequest";
import Navbar from "./components/Navbar/Navbar";
import UserNavbar from "./components/Navbar/UserNavbar";
import Rates from "./containers/Rates/Rates"

import UserPrincipal from "./containers/User/Principal";
import UserHelp from "./containers/User/Help";
import UserProducts from "./containers/User/Products";
import UserExpenses from "./containers/User/Expenses";
import { CLIENT } from "./components/PrivateRoute/routesByRole";
import { NotFound } from "./containers/NotFound/NotFound";

const App = () => {
  const isClientL = () => localStorage.getItem("role") === CLIENT;
  const isLoggedInL = () => !!localStorage.getItem("token");

  const [isLoggedIn, setIsLoggedIn] = useState(isLoggedInL());
  const [isClient, setIsClient] = useState(isClientL());

  const handleSetIsLoggedIn = (value) => setIsLoggedIn(value);
  const handleSetIsClient = (value) => setIsClient(value);

  return (
    <Router>
      {isLoggedIn ? (
        isClient ? (
          <UserNavbar
            setIsLoggedIn={handleSetIsLoggedIn}
            setIsClient={handleSetIsClient}
          />
        ) : (
          <Navbar
            setIsLoggedIn={handleSetIsLoggedIn}
            setIsClient={handleSetIsClient}
          />
        )
      ) : (
        <div />
      )}
      <Switch>
        <PrivateRoute
          exact
          path="/"
          component={isClient ? UserPrincipal : Principal}
        />
        <PrivateRoute
          path="/login"
          component={Login}
          setIsLoggedIn={handleSetIsLoggedIn}
          setIsClient={handleSetIsClient}
        />
        <PrivateRoute path="/checkin" component={CheckIn} />
        <Route path="/rates" component={Rates} />
        <PrivateRoute path="/reservation" component={Reservation} />
        <PrivateRoute path="/registration" component={Registration} />
        <PrivateRoute exact path="/checkout" component={CheckOut} />
        <PrivateRoute
          exact
          path="/checkout/:reservationId/expenses"
          component={CheckOutExpenses}
        />
        <PrivateRoute path="/reservations" component={Reservations} />
        <PrivateRoute exact path="/products/:id" component={UserProducts} />
        <PrivateRoute exact path="/products" component={Products} />
        <PrivateRoute
          exact
          path="/products/newProduct"
          component={NewProduct}
        />
        <PrivateRoute path="/orders" component={Orders} />
        <PrivateRoute exact path="/help" component={HelpRequest} />
        <PrivateRoute exact path="/help/:id" component={UserHelp} />
        <PrivateRoute path="/expenses/:id" component={UserExpenses} />
        <Route>
          <NotFound />
        </Route>
      </Switch>
    </Router>
  );
};

export default App;
