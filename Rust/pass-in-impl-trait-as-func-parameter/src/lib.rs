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
    use crate::cars::mazda::Mazda;

    #[test]
    fn test_mazda_car_should_return_mazda_make() {
        let mazda_car = Mazda::new();
        let make = get_make(&mazda_car);
        assert_eq!("Mazda", make);
    }
}
