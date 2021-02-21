import moment from "moment";


export const busyRoomsColumns = [
    {id: "id", label: "room.singular"},
    {
        id: "startDate",
        label: "room.room.from",
        format: (date) => {
            return moment(date).format("DD/MM/YYYY");
        },
    },
    {
        id: "endDate",
        label: "room.room.until",
        format: (date) => moment(date).format("DD/MM/YYYY"),
    },
    {id: "userEmail", label: "room.room.owner"},
];


export const helpListColumns = [
    {id: "id", label: "help.request.plural"},
    {id: "helpStep", label: "help.status.message"},
    {id: "helpText", label: "help.text.singular"},
    {id: "roomNumber", label: "room.singular"}
];


export const ordersColumns = [
    {id: "chargeId", label: "reservation.order.plural"},
    {id: "description", label: "product.description"},
    {id: "roomNumber", label: "room.singular"},
    {id: "action", label: "help.status.mark.message"}
];


export const productsColumns = [
    {id: "file", label: " "},
    {id: "description", label: "product.description"},
    {id: "price", label: "user.price"}
];


export const reservationUserColumns = [
  { id: "roomType", label: "reservation.room.type" },
  {
    id: "startDate",
    label: "reservation.date.start",
    format: (date) => {
      return moment(date).format("DD/MM/YYYY");
    },
  },
  {
    id: "endDate",
    label: "reservation.date.end",
    format: (date) => moment(date).format("DD/MM/YYYY"),
  },
  { id: "roomNumber", label: "reservation.room.number" },
  {
    id: "actions",
    label: "user.actions",
    isButton: true,
  },
  {
    id: "expenses",
    label: "user.expenses",
    isButton: true,
  },
  {
    id: "help",
    label: "user.problem",
    isButton: true,
  },
];


export const reservationsColumns = [
    {id: "hash", label: "reservation.id"},
    {id: "userEmail", label: "reservation.email"},
    {
        id: "startDate", label: "reservation.date.start", format: (date) => {
            return moment(date).format("DD/MM/YYYY");
        },
    },
    {
        id: "endDate", label: "reservation.date.end", format: (date) => {
            return moment(date).format("DD/MM/YYYY");
        },
    },
    {id: "isActive", label: "reservation.isActive"}
]


export const rateColumns = [
  {id: "roomNumber", label: "RoomNumber"},
  {id: "userEmail", label: "reservation.email"},
  {
      id: "startDate", label: "reservation.date.start", format: (date) => {
          return moment(date).format("DD/MM/YYYY");
      },
  },
  {
      id: "endDate", label: "reservation.date.end", format: (date) => {
          return moment(date).format("DD/MM/YYYY");
      },
  },
  {id: "calification", label: "Rate"}
]
