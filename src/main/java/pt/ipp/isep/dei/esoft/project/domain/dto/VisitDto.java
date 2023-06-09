package pt.ipp.isep.dei.esoft.project.domain.dto;

import java.time.LocalDate;
import java.util.Objects;

public class VisitDto {

        /**
         * The Id.
         */
        private Integer id = 0;
        /**
         * The id iteration variable.
         */
        private static int counter = 0;
        /**
         * The Start hour.
         */
        private Integer startHour;
        /**
         * The End hour.
         */
        private Integer endHour;
        /**
         * The Visit date.
         */
        private LocalDate visitDate;
        /**
         * The User name.
         */
        private String userName;
        /**
         * The User phone number.
         */
        private String userPhoneNumber;
        /**
         * The Acceptance status.
         */
        private Boolean acceptanceStatus;

        /**
         * Instantiates a new Visit.
         *
         * @param visitDate       the visit date
         * @param startHour       the start hour
         * @param endHour         the end hour
         * @param userName        the user name
         * @param userPhoneNumber the user phone number
         */
        public VisitDto(LocalDate visitDate, Integer startHour, Integer endHour, String userName, String userPhoneNumber) {
                this.id = counter++;
                this.startHour = startHour;
                this.endHour = endHour;
                this.visitDate = visitDate;
                this.userName = userName;
                this.userPhoneNumber = userPhoneNumber;
                this.acceptanceStatus = false;
        }

        public VisitDto(LocalDate visitDate, Integer startHour, Integer endHour, String userName, String userPhoneNumber, Integer id, Boolean acceptanceStatus) {
                this.id = id;
                this.startHour = startHour;
                this.endHour = endHour;
                this.visitDate = visitDate;
                this.userName = userName;
                this.userPhoneNumber = userPhoneNumber;
                this.acceptanceStatus = acceptanceStatus;
        }

        /**
         * Gets visit id.
         *
         * @return the id
         */
        public Integer getId() {
                return this.id;
        }

        /**
         * Gets start hour.
         *
         * @return the start hour
         */
        public Integer getStartHour() {
                return this.startHour;
        }

        /**
         * Gets end hour.
         *
         * @return the end hour
         */
        public Integer getEndHour() {
                return this.endHour;
        }

        /**
         * Gets visit date.
         *
         * @return the visit date
         */
        public LocalDate getVisitDate() {
                return this.visitDate;
        }

        /**
         * Gets user name.
         *
         * @return the user name
         */
        public String getUserName() {
                return userName;
        }

        /**
         * Gets user phone number.
         *
         * @return the user phone number
         */
        public String getUserPhoneNumber() {
                return userPhoneNumber;
        }

        /**
         * Equals boolean.
         *
         * @param o the o
         * @return the boolean
         */
        @Override
        public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;
                VisitDto visitDto = (VisitDto) o;
                return Objects.equals(startHour, visitDto.startHour) && Objects.equals(endHour, visitDto.endHour) && Objects.equals(visitDate, visitDto.visitDate) && Objects.equals(userName, visitDto.userName) && Objects.equals(userPhoneNumber, visitDto.userPhoneNumber) && Objects.equals(acceptanceStatus, visitDto.acceptanceStatus);
        }

        /**
         * Hash code int.
         *
         * @return the int
         */
        @Override
        public int hashCode() {
                return Objects.hash(startHour, endHour, visitDate, userName, userPhoneNumber, acceptanceStatus);
        }

}