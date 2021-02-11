import React from 'react';
import { makeStyles } from '@material-ui/core/styles';
import InputLabel from '@material-ui/core/InputLabel';
import MenuItem from '@material-ui/core/MenuItem';
import FormControl from '@material-ui/core/FormControl';
import Select from '@material-ui/core/Select';
import {useTranslation} from "react-i18next";

const useStyles = makeStyles((theme) => ({
  formControl: {
    margin: theme.spacing(1),
    minWidth: 120,
  },
  selectEmpty: {
    marginTop: theme.spacing(2),
  },
}));

const SimpleSelect = (props) => {
  const classes = useStyles();
  const {t} = useTranslation();

  const {onChange, options} = props;


  return (
      <FormControl className={classes.formControl}>
        <InputLabel id="demo-simple-select-label">{t('room.singular')}</InputLabel>
        <Select
          labelId="demo-simple-select-label"
          id="demo-simple-select"
          onChange={onChange}
        >
          {options.forEach(element => {
            <MenuItem value={element.value}>{element.label}</MenuItem>
          })}
        </Select>
      </FormControl>
     
  );
}

export default SimpleSelect;
