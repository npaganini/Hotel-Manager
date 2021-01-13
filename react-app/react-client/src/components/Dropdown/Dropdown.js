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
  const [room, setRoom] = React.useState('');
  const {t} = useTranslation();

  const handleChange = (event) => {
    setRoom(event.target.value);
  };

  return (
      <FormControl className={classes.formControl}>
        <InputLabel id="demo-simple-select-label">{t('room.singular')}</InputLabel>
        <Select
          labelId="demo-simple-select-label"
          id="demo-simple-select"
          value={room}
          onChange={handleChange}
        >
          <MenuItem value={10}>10</MenuItem>
          <MenuItem value={20}>20</MenuItem>
          <MenuItem value={30}>30</MenuItem>
        </Select>
        {/* <FormHelperText>Campo Requerido</FormHelperText> */}
      </FormControl>
     
  );
}

export default SimpleSelect;
