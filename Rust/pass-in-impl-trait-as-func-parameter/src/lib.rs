use cars::icar::ICar;

mod cars;

pub fn get_make(car: &impl ICar) -> String {
    let make = car.get_make();
    println!("Car make is: {}", make);
    make
}

#[cfg(test)]
mod tests {
    use super::*;
    use crate::cars::{mazda::Mazda, toyota::Toyota};
    use pretty_assertions::assert_eq;

    #[test]
    fn test_mazda_car_should_return_mazda_make() {
        let mazda_car = Mazda::new();
        let make = get_make(&mazda_car);
        assert_eq!("Mazda", make);
    }

    #[test]
    fn test_toyota_car_should_return_mazda_make() {
        let toyota_car = Toyota::new();
        let make = get_make(&toyota_car);
        assert_eq!("Toyota", make);
    }
}
