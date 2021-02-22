import React from 'react';
import { makeStyles } from '@material-ui/core/styles';
import InputLabel from '@material-ui/core/InputLabel';
import FormControl from '@material-ui/core/FormControl';
import Select from '@material-ui/core/Select';
import FormHelperText from '@material-ui/core/FormHelperText';
import { useTranslation } from "react-i18next";

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
  const { t } = useTranslation();

  const { onChange, options, error, required, helperText } = props;

  let list = [];

  options.forEach(element => {
    list.push(<option id={element.id} value={element.id}>{element.number}</option>);
  })

  return (
    <FormControl className={classes.formControl} error={error} required={required}>
      <InputLabel id="demo-simple-select-label">{t('room.singular')}</InputLabel>
      <Select
        labelId="demo-simple-select-label"
        id="demo-simple-select"
        onChange={onChange}
      >
        {list}
      </Select>
      <FormHelperText>{helperText}</FormHelperText>
    </FormControl>
  );
}

export default SimpleSelect;
