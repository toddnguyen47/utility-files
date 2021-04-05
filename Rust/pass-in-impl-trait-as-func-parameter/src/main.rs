use cars_impl::cars::mazda::Mazda;

fn main() {
    let mazda = Mazda::new();
    let make = cars_impl::get_make(&mazda);
    println!("Make is: '{}'", make);
}
