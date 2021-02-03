package adPortal.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class Advertisment implements Serializable {

    private  String title;
    private  String text;
    private  Double price;
    private Date date;
    private  Category category;
    private User user;

    public Advertisment() {
    }

    public Advertisment(String title, String text, Double price, Date date, Category category, User user) {
        this.title = title;
        this.text = text;
        this.price = price;
        this.date = date;
        this.category = category;
        this.user = user;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Advertisment that = (Advertisment) o;
        return Objects.equals(title, that.title) &&
                Objects.equals(text, that.text) &&
                Objects.equals(price, that.price) &&
                Objects.equals(date, that.date) &&
                category == that.category &&
                Objects.equals(user, that.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, text, price, date, category, user);
    }

    @Override
    public String toString() {
        return "Advertisment{" +
                "title='" + title + '\'' +
                ", text='" + text + '\'' +
                ", price=" + price +
                ", date=" + date +
                ", category=" + category +
                ", user=" + user +
                '}';
    }
}
