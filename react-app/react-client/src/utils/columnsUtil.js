import moment from "moment";

export const busyRoomsColumns = [
  { id: "id", label: "Habitación" },
  {
    id: "startDate",
    label: "Desde",
    format: (date) => {
      return moment(date).format("DD/MM/YYYY");
    },
  },
  {
    id: "endDate",
    label: "Hasta",
    format: (date) => moment(date).format("DD/MM/YYYY"),
  },
  { id: "userEmail", label: "Titular" },
];

export const reservationUserColumns = [
  { id: "roomType", label: "Room Type" },
  {
    id: "startDate",
    label: "Desde",
    format: (date) => {
      return moment(date).format("DD/MM/YYYY");
    },
  },
  {
    id: "endDate",
    label: "Hasta",
    format: (date) => moment(date).format("DD/MM/YYYY"),
  },
  { id: "roomNumber", label: "Room Number" },
  {
    id: "actions",
    label: "Actions",
    isButton: true,
  },
  {
    id: "expenses",
    label: "Expenses",
    isButton: true,
  },
  {
    id: "help",
    label: "Problem?",
    isButton: true,
  },
];
