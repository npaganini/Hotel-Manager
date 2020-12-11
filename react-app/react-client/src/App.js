import React, { Component } from 'react';
import './App.css';

import Navbar from './components/Navbar/Navbar'
import Table from './components/Table/Table'
import DatePicker from './components/DatePickers/DatePicker'
import Button from './components/Button/Button'
import Input from './components/Input/Input'
import Dropdown from './components/Dropdown/Dropdown'
import Login from './components/Login/Login'

class App extends Component {
  render() {
    return (
      // <Navbar></Navbar>
      // <Table></Table>
      // <DatePicker label="Desde"></DatePicker>
      // <div>
      //   <Button ButtonType="Delete"></Button>
      //   <Button ButtonType="Save"></Button>
      //   <Button ButtonType="Back"></Button>
      // </div>
      // <Input type="text" label="test"></Input>
      <Login></Login>
    );
  }
}

export default App;
