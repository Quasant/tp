package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.company.Company;
import seedu.address.model.company.UniqueCompanyList;
import seedu.address.model.job.Job;
import seedu.address.model.job.UniqueJobList;
import seedu.address.model.person.Person;
import seedu.address.model.person.UniquePersonList;

/**
 * Wraps all data at the address-book level
 * Duplicates are not allowed (by .isSamePerson comparison)
 */
public class AddressBook implements ReadOnlyAddressBook {

    private final UniquePersonList persons;
    private final UniqueJobList jobs;
    private final UniqueCompanyList companies;


    // The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
    // between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
    //
    // Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
    //   among constructors.
    {
        persons = new UniquePersonList();
        jobs = new UniqueJobList();
        companies = new UniqueCompanyList();
    }

    public AddressBook() {
    }

    /**
     * Creates an AddressBook using the Persons in the {@code toBeCopied}
     */
    public AddressBook(ReadOnlyAddressBook toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the person list with {@code persons}.
     * {@code persons} must not contain duplicate persons.
     */
    public void setPersons(List<Person> persons) {
        this.persons.setPersons(persons);
    }

    /** Replaces the contents of the job list with {@code jobs}. */
    public void setJobs(List<Job> jobs) {
        this.jobs.setJobs(jobs);
    }

    /** Replaces the contents of the company list with {@code companies}. */
    public void setCompanies(List<Company> companies) {
        this.companies.setCompanies(companies);
    }
    /**
     * Resets the existing data of this {@code AddressBook} with {@code newData}.
     */
    public void resetData(ReadOnlyAddressBook newData) {
        requireNonNull(newData);

        setPersons(newData.getPersonList());
        setJobs(newData.getJobList());
        setCompanies(newData.getCompanyList());
    }

    //// person-level operations

    /**
     * Returns true if a person with the same identity as {@code person} exists in the address book.
     */
    public boolean hasPerson(Person person) {
        requireNonNull(person);
        return persons.contains(person);
    }

    /**
     * Returns true if a job that has the same name and company as {@code job} exists in the address book.
     */
    public boolean hasJob(Job job) {
        requireNonNull(job);
        return jobs.contains(job);
    }

    /**
     * Returns true if a company with the same name as <code>company</code> exists in the address book.
     *
     * @param company Company to be checked.
     * @return true if address book contains company.
     */
    public boolean hasCompany(Company company) {
        requireNonNull(company);
        return companies.contains(company);
    }

    /**
     * Adds a person to the address book.
     * The person must not already exist in the address book.
     */
    public void addPerson(Person p) {
        persons.add(p);
    }

    /** Adds a job to the address book. */
    public void addJob(Job j) {
        jobs.add(j);
    }

    /**
     * Adds a company to the address book.
     *
     * @param c Company to be added.
     */
    public void addCompany(Company c) {
        companies.add(c);
    }

    /**
     * Replaces the given person {@code target} in the list with {@code editedPerson}.
     * {@code target} must exist in the address book.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the address book.
     */
    public void setPerson(Person target, Person editedPerson) {
        requireNonNull(editedPerson);

        persons.setPerson(target, editedPerson);
    }

    /**
     * Replaces the given company in the list with an edited one.
     *
     * @param target Company to be replaced.
     * @param editedCompany Company to replace the other.
     */
    public void setCompany(Company target, Company editedCompany) {
        requireNonNull(editedCompany);

        companies.setCompany(target, editedCompany);
    }

    /**
     * Removes {@code key} from this {@code AddressBook}.
     * {@code key} must exist in the address book.
     */
    public void removePerson(Person key) {
        persons.remove(key);
    }

    public void removeJob(Job key) {
        jobs.remove(key);
    }

    /**
     * Removes a company from this address book.
     *
     * @param key Company to be removed.
     */
    public void removeCompany(Company key) {
        companies.remove(key);
    }

    //// util methods

    @Override
    public ObservableList<Person> getPersonList() {
        return persons.asUnmodifiableObservableList();
    }

    @Override
    public ObservableList<Job> getJobList() {
        return jobs.asUnmodifiableObservableList();
    }

    @Override
    public ObservableList<Company> getCompanyList() {
        return companies.asUnmodifiableObservableList();
    }

    @Override
    public int hashCode() {
        return persons.hashCode(); // TODO: this needs to be changed to include all lists
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {

            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AddressBook)) {
            return false;
        }

        AddressBook otherAddressBook = (AddressBook) other;
        return persons.equals(otherAddressBook.persons)
                && jobs.equals(otherAddressBook.jobs)
                && companies.equals(otherAddressBook.companies);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("persons", persons)
                .add("jobs", jobs)
                .add("companies", companies)
                .toString();
    }
}
