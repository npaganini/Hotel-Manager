import React from 'react';
import Button from '@material-ui/core/Button';
import { makeStyles } from '@material-ui/core/styles';
import DeleteIcon from '@material-ui/icons/Delete';
import BackIcon from '@material-ui/icons/Backspace';
import SaveIcon from '@material-ui/icons/Save';

const useStyles = makeStyles((theme) => ({
    button: {
        margin: theme.spacing(1),
    },
}));

const IconLabelButtons = (props) => {
    const classes = useStyles();

    switch (props.ButtonType) {
        case "Delete":
            return (
                <Button
                    variant="contained"
                    color="secondary"
                    className={classes.button}
                    startIcon={<DeleteIcon />}
                >
                    Borrar
                </Button>
            );

        case "Save":
            return (

                <Button
                    variant="contained"
                    color="primary"
                    className={classes.button}
                    startIcon={<SaveIcon />}
                >
                    Guardar
                </Button>
            );
        
            case "Back":
                return (

                    <Button
                        variant="contained"
                        color="default"
                        className={classes.button}
                        startIcon={<BackIcon />}
                    >
                        Volver
                    </Button>
                );
                
            default:
                return (
                    <div></div>
                );
    }
}

export default IconLabelButtons;